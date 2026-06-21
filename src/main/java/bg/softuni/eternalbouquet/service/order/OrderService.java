package bg.softuni.eternalbouquet.service.order;

import bg.softuni.eternalbouquet.mapper.order.OrderMapper;
import bg.softuni.eternalbouquet.model.dto.order.CheckoutRequest;
import bg.softuni.eternalbouquet.model.dto.order.OrderDto;
import bg.softuni.eternalbouquet.model.entity.bouquet.Bouquet;
import bg.softuni.eternalbouquet.model.entity.order.Order;
import bg.softuni.eternalbouquet.model.entity.order.OrderStatus;
import bg.softuni.eternalbouquet.model.entity.orderItem.OrderItem;
import bg.softuni.eternalbouquet.model.entity.user.User;
import bg.softuni.eternalbouquet.repository.bouquet.BouquetRepository;
import bg.softuni.eternalbouquet.repository.order.OrderRepository;
import bg.softuni.eternalbouquet.repository.orderItem.OrderItemRepository;
import bg.softuni.eternalbouquet.repository.user.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class OrderService {
    private final OrderRepository orderRepository;
    private final UserRepository userRepository;
    private final BouquetRepository bouquetRepository;
    private final OrderItemRepository orderItemRepository;

    public OrderService(OrderRepository orderRepository, UserRepository userRepository, BouquetRepository bouquetRepository, OrderItemRepository orderItemRepository) {
        this.orderRepository = orderRepository;
        this.userRepository = userRepository;
        this.bouquetRepository = bouquetRepository;
        this.orderItemRepository = orderItemRepository;
    }

    public OrderDto createOrder(UUID userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found."));

        Order order = new Order();

        order.setCreatedOn(LocalDateTime.now());
        order.setStatus(OrderStatus.PENDING);
        order.setUser(user);
        order.setItems(new ArrayList<>());
        order.setTotalPrice(BigDecimal.ZERO);

        orderRepository.save(order);

        return OrderMapper.toDto(order);
    }

    public List<OrderDto> getOrdersByUserId(UUID userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        return orderRepository.findByUserOrderByCreatedOnDesc(user)
                .stream()
                .map(OrderMapper::toDto)
                .toList();
    }

    public OrderDto getById(UUID id) {
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Order not found"));
        return OrderMapper.toDto(order);
    }


    public void addToCart(UUID userId, UUID bouquetId){
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Bouquet bouquet = bouquetRepository.findById(bouquetId)
                .orElseThrow(() -> new RuntimeException("Bouquet not found"));

        Order order = orderRepository.findByUserAndStatus(user, OrderStatus.PENDING)
                .orElse(null);

        if(order == null){
            order = createCart(user);
        }

        OrderItem item = orderItemRepository.findByOrderAndBouquet(order, bouquet)
                .orElse(null);

        if(item == null){
            item = new OrderItem();
            item.setOrder(order);
            item.setBouquet(bouquet);
            item.setQuantity(1);
            item.setPriceAtPurchase(bouquet.getPrice());
        } else {
            item.setQuantity(item.getQuantity() + 1);
        }

        orderItemRepository.save(item);

        calculateTotal(order);

    }

    private void calculateTotal(Order order) {

        BigDecimal total = order.getItems()
                .stream()
                .map(item ->
                        item.getPriceAtPurchase()
                                .multiply(BigDecimal.valueOf(item.getQuantity())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        order.setTotalPrice(total);

        orderRepository.save(order);
    }

    private Order createCart(User user) {
        Order order = new Order();

        order.setUser(user);
        order.setCreatedOn(LocalDateTime.now());
        order.setStatus(OrderStatus.PENDING);
        order.setTotalPrice(BigDecimal.ZERO);

        return orderRepository.save(order);
    }


    public OrderDto getCurrentCart(UUID userId) {

         Order order = orderRepository.findByUserIdAndStatus(userId, OrderStatus.PENDING)
                .orElseGet(() -> {
                    User user = userRepository.findEntityById(userId);

                    Order newOrder = new Order();
                    newOrder.setUser(user);
                    newOrder.setCreatedOn(LocalDateTime.now());
                    newOrder.setStatus(OrderStatus.PENDING);
                    newOrder.setTotalPrice(BigDecimal.ZERO);

                    return orderRepository.save(newOrder);
                });

         return OrderMapper.toDto(order);
    }

    public void removeItem(UUID itemId) {

        OrderItem item = orderItemRepository.findById(itemId)
                .orElseThrow(() -> new RuntimeException("Order item not found"));

        Order order = item.getOrder();

        orderItemRepository.delete(item);
        order.getItems().remove(item);

        order.setTotalPrice(
                order.getItems()
                        .stream()
                        .map(i -> i.getBouquet().getPrice())
                        .reduce(BigDecimal.ZERO, BigDecimal::add)
        );

        orderRepository.save(order);
    }

    @Transactional
    public void checkout(UUID userId, CheckoutRequest request) {

        Order order = orderRepository.findByUserIdAndStatus(userId, OrderStatus.PENDING)
                .orElseThrow(() -> new RuntimeException("Cart not found"));

        if(order.getItems() == null  || order.getItems().isEmpty()){
            throw new RuntimeException("Cart is empty");
        }

        for (OrderItem item : order.getItems()) {

            Bouquet bouquet = item.getBouquet();

            int newStock = bouquet.getStock() - item.getQuantity();

            if(newStock < 0) {
                throw new RuntimeException("Not enough stock for: " + bouquet.getName());
            }

            bouquet.setStock(newStock);

        }

        BigDecimal total = order.getItems().stream()
                        .map(i -> i.getPriceAtPurchase()
                                .multiply(BigDecimal.valueOf(i.getQuantity())))
                                .reduce(BigDecimal.ZERO, BigDecimal::add);

        order.setStatus(OrderStatus.COMPLETED);
        order.setCreatedOn(LocalDateTime.now());
        order.setDeliveryAddress(request.getDeliveryAddress());
        order.setTotalPrice(total);

        orderRepository.save(order);

    }
}


