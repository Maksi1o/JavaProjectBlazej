package pd6;

import pd6.model.Product;
import pd6.model.User;
import pd6.repository.Repository;
import pd6.service.UserService;

import java.util.Collection;

public class Main {
    public static void main(String[] args) {
        Repository<Long, User> users = new Repository<>();

        users.addEntity(1L, new User(1L, "Bartek"));
        users.addEntity(2L, new User(2L, "Wojtek"));
        users.addEntity(3L, new User(3L, "Jarek"));
        System.out.println(users.findEntity(5L));
        users.removeEntity(2L);

        Collection<User> allUsers = users.getAllEntities();
        System.out.println(allUsers);

        UserService userService = new UserService(users);
        userService.changeName(1L, "Mieczyslaw");

        System.out.println(allUsers);

        Repository<String, Product> products = new Repository<>();

        products.addEntity("Laptop", new Product("Hp eliteBook", 2500));
        products.addEntity("Telefon", new Product("Blueberry", 500));
        products.addEntity("Karta Graficzna", new Product("Nvidia RTX 5090", 30000));
        System.out.println(products.findEntity("Laptop"));
        products.removeEntity("Laptop");

        Collection<Product> allProducts = products.getAllEntities();
        System.out.println(allProducts);
    }
}
