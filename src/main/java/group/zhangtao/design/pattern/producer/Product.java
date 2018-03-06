package group.zhangtao.design.pattern.producer;

import java.util.UUID;

public class Product {
    private UUID uuid;
    public Product(UUID uuid){
        this.uuid=uuid;
    }

    public UUID getUuid() {
        return uuid;
    }
}
