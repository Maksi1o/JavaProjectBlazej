package pd6;

import pd6.model.Product;
import pd6.model.User;
import pd6.repository.Repository;
import pd6.service.UserService;

import java.util.Collection;

public class Main {
    public static void main(String[] args) {
        Repository<Long, User> users = new Repository<>();

        users.save(new User(1L, "Bartek"));
        users.save(new User(2L, "Wojtek"));
        users.save(new User(3L, "Jarek"));
        System.out.println(users.findById(5L));

        users.deleteById(2L);

        Collection<User> allUsers = users.getAll();
        System.out.println(allUsers);

        UserService userService = new UserService(users);
        userService.changeName(1L, "Mieczyslaw");
        System.out.println(allUsers);

        Repository<String, Product> products = new Repository<>();

        products.save(new Product("Hp eliteBook", 2500));
        products.save(new Product("Blueberry", 500));
        products.save(new Product("Nvidia RTX 5090", 30000));

        System.out.println(products.findById("Hp eliteBook"));

        products.deleteById("Hp eliteBook");

        Collection<Product> allProducts = products.getAll();
        System.out.println(allProducts);
    }
}