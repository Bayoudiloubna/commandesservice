package com.example.commandesservice.service;

import com.example.commandesservice.repository.CommandeRepository;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommandeService {

    private final CommandeRepository commandeRepository;

    public CommandeService(CommandeRepository commandeRepository) {
        this.commandeRepository = commandeRepository;
    }

    public List<Commande> getAllCommandes() {
        return commandeRepository.findAll();
    }

    public Commande saveCommande(Commande commande) {
        return commandeRepository.save(commande);
    }

    @HystrixCommand(fallbackMethod = "fallbackCheckProductAvailability")
    public boolean checkProductAvailability(Long productId) {
        // Logique pour communiquer avec le microservice Produits (via GraphQL ou REST)
        // Exemple fictif d'appel au service Produits
        throw new RuntimeException("Le service Produits est indisponible !");
    }

    public boolean fallbackCheckProductAvailability(Long productId) {
        // Retourne une réponse par défaut en cas de panne du microservice Produits
        return false;
    }
}
