package org.example;

import ecommerce.ProductInfoGrpc;
import ecommerce.ProductInfoOuterClass;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

public class ProductInfoClient {
    public static void main(String[] args) {
        ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", 50051).usePlaintext().build();

        ProductInfoGrpc.ProductInfoBlockingStub stub = ProductInfoGrpc.newBlockingStub(channel);

        ProductInfoOuterClass.ProductID productID = stub.addProduct(ProductInfoOuterClass.Product.newBuilder().setName("Apple iPhone 17 Air").setDescription("New lineup of the thinnest iPhones ever").setPrice(999).build());
        System.out.println(productID.getValue());

        ProductInfoOuterClass.Product product = stub.getProduct(productID);
        System.out.println(product.toString());
        channel.shutdown();
    }
}