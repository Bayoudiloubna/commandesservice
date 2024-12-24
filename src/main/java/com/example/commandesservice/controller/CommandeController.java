package com.example.commandesservice.controller;

import com.example.commandesservice.model.Commande;
import com.example.commandesservice.service.CommandeService;  // Assurez-vous d'avoir un service pour la logique métier
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/commandes")  // Définir l'URL de base pour les commandes
public class CommandeController {

    @Autowired
    private CommandeService commandeService;  // Le service pour la gestion des commandes

    // Récupérer toutes les commandes
    @GetMapping
    public List<Commande> getAllCommandes() {
        return commandeService.getAllCommandes();
    }

    // Récupérer une commande par son ID
    @GetMapping("/{id}")
    public ResponseEntity<Commande> getCommandeById(@PathVariable Long id) {
        Optional<Commande> commande = commandeService.getCommandeById(id);
        return commande.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    // Créer une nouvelle commande
    @PostMapping
    public ResponseEntity<Commande> createCommande(@RequestBody Commande commande) {
        Commande newCommande = commandeService.createCommande(commande);
        return ResponseEntity.status(HttpStatus.CREATED).body(newCommande);
    }

    // Mettre à jour une commande existante
    @PutMapping("/{id}")
    public ResponseEntity<Commande> updateCommande(@PathVariable Long id, @RequestBody Commande commande) {
        Optional<Commande> updatedCommande = commandeService.updateCommande(id, commande);
        return updatedCommande.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    // Supprimer une commande par son ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCommande(@PathVariable Long id) {
        boolean isDeleted = commandeService.deleteCommande(id);
        return isDeleted ? ResponseEntity.noContent().build()
                : ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }
}
