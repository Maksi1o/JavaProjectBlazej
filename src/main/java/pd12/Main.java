package pd12;

import pd12.model.Order;
import pd12.model.OrderResult;
import pd12.service.OrderService;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public class Main {
    public static void main(String[] args) {
        List<Order> orders = List.of(
            new Order("Bartek", 3),
            new Order("Anna", 5),
            new Order("Wojtek", 5),
            new Order("Zuza", 2));

        OrderService store = new OrderService();

        List<CompletableFuture<OrderResult>> futures = orders.stream()
            .map(order -> CompletableFuture.supplyAsync(() -> store.processOrder(order)))
            .toList();

        futures.forEach(future -> System.out.println(future.join()));

        System.out.println("Sold: " + store.getSoldCount() + "\nAvailable: " + store.getAvailableQuantity());
    }
}