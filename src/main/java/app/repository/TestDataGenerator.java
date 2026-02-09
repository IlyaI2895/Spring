package app.repository;

import app.model.Product;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class TestDataGenerator {

    public static List<Product> generateRandomBooks(int count) {
        Random random = new Random();
        String[] name = {
                "Wireless Bluetooth Earbuds", "Stainless Steel Water Bottle", "LED Desk Lamp", "Mechanical Gaming Keyboard",
                "USB-C Fast Charger", "Yoga Mat Premium", "Smart Fitness Watch", "Classic Leather Wallet",
                "Portable Power Bank", "Copper Coffee Mug", "Organic Cotton T-Shirt", "Bluetooth Portable Speaker",
                "Laptop Stand Aluminum", "Digital Kitchen Scale", "Essential Oil Diffuser", "Graphic Gaming Mousepad",
                "Hardcover Notebook Set", "Wireless Charging Pad", "Reusable Shopping Bags", "Smartphone Camera Lens Kit",
                "Noise Cancelling Headphones", "Selfie Ring Light", "Ceramic Plant Pot", "Pocket-Size Multitool",
                "Luxury Silk Sleep Mask", "Collapsible Silicone Folders", "Electric Kettle Glass", "Ergonomic Office Chair",
                "Resistance Exercise Bands", "4K Action Camera", "Natural Bamboo Cutting Board", "Magnetic Phone Car Mount",
                "Handheld Milk Frother", "Reusable Makeup Remover Pads", "Adjustable Laptop Backpack",
                "Aromatherapy Shower Steamers", "UV Phone Sanitizer", "Stainless Steel Lunch Box",
                "Cordless Electric Screwdriver", "Smart LED Light Strip", "Insulated Travel Mug", "Memory Foam Slippers",
                "Acrylic Paint Set", "Folding Phone Stand", "Eco-Friendly Laundry Detergent", "Desktop Cable Organizer",
                "Mini Projector Portable", "Galaxy Projection Night Light", "Herbal Tea Sampler Box", "Touch Screen Gloves"


        };

        boolean[] inStock = { true,false,true,false
        };


        Product[] products = new Product[count];
        for (int i = 0; i < count; i++) {
            String prodName = name[random.nextInt(name.length)];
            boolean stock = inStock[random.nextInt(inStock.length)];
            double price = (random.nextInt(100000) / random.nextInt(1000));


            products[i] = Product.builder()
                    .name(prodName)
                    .inStock(stock)
                    .price(price)
                    .build();
        }

        return new ArrayList<>(Arrays.asList(products));
    }
}

