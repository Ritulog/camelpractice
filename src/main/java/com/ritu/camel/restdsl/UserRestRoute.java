package com.ritu.camel.restdsl;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

@Component
public class UserRestRoute extends RouteBuilder {

    private final UserRepository userRepository;

    public UserRestRoute(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @Override
    public void configure() throws Exception {

        restConfiguration()
            .component("servlet")
            .port(8081)
            .bindingMode(org.apache.camel.model.rest.RestBindingMode.json);

        // ===== REST DSL =====
        rest("/users")

            // GET all users
            .get()
                .to("direct:getAllUsers")

            // GET user by id
            .get("/{id}")
                .to("direct:getUserById")

            // CREATE new user
            .post()
                .type(User.class)
                .to("direct:createUser")

            // UPDATE user
            .put("/{id}")
                .type(User.class)
                .to("direct:updateUser")

            // DELETE user
            .delete("/{id}")
                .to("direct:deleteUser");


        // ===== ROUTES =====
        from("direct:getAllUsers")
                .routeId("get-all-users-route")
                .process(exchange -> {
                    exchange.getIn().setBody(userRepository.findAll());
                });



        from("direct:getUserById")
            .process(exchange -> {
                String id = exchange.getIn().getHeader("id", String.class);
                User user = exchange.getContext()
                        .getRegistry()
                        .lookupByNameAndType("userRepository", UserRepository.class)
                        .findById(id)
                        .orElseThrow(() -> new RuntimeException("User not found"));
                exchange.getIn().setBody(user);
            });

        from("direct:createUser")
                .routeId("create-user-route")

                .log("BODY CLASS = ${body.getClass}")
                .log("USER OBJECT = ${body}")

                .to("bean:userRepository?method=save")

                .log("AFTER SAVE USER = ${body}");

        from("direct:updateUser")
            .process(exchange -> {
                String id = exchange.getIn().getHeader("id", String.class);
                User newUser = exchange.getIn().getBody(User.class);
                UserRepository repo = exchange.getContext()
                        .getRegistry()
                        .lookupByNameAndType("userRepository", UserRepository.class);

                User existing = repo.findById(id)
                        .orElseThrow(() -> new RuntimeException("User not found"));

                existing.setName(newUser.getName());
                existing.setEmail(newUser.getEmail());

                exchange.getIn().setBody(repo.save(existing));
            });

        from("direct:deleteUser")
            .process(exchange -> {
                String id = exchange.getIn().getHeader("id", String.class);
                UserRepository repo = exchange.getContext()
                        .getRegistry()
                        .lookupByNameAndType("userRepository", UserRepository.class);

                repo.deleteById(id);
                exchange.getIn().setBody("Deleted user with id: " + id);
            });
    }
}
