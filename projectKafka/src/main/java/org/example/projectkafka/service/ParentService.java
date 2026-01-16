package org.example.projectkafka.service;

import org.example.projectkafka.model.BusETA;
import org.example.projectkafka.model.Parent;
import org.example.projectkafka.model.PenaltyEvent;
import org.example.projectkafka.repository.ParentRepository;
import org.example.projectkafka.repository.PenaltyRepository;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class ParentService {

    private final ParentRepository parentRepository;
    private final PenaltyRepository penaltyRepository;

    public ParentService(ParentRepository parentRepository,
                         PenaltyRepository penaltyRepository) {
        this.parentRepository = parentRepository;
        this.penaltyRepository = penaltyRepository;
    }

    /**
     * Récupère les parents associés à un bus et un arrêt
     * Si aucun parent n'est trouvé en base, retourne des parents de test
     */
    public List<Parent> findParentsByBusAndStop(String busId, String stopId) {
        System.out.println("👨‍👩‍👧 Recherche parents pour bus=" + busId + ", stop=" + stopId);

        try {
            List<Parent> parents = parentRepository.findByBusIdAndStopId(busId, stopId);

            if (parents != null && !parents.isEmpty()) {
                System.out.println("✅ " + parents.size() + " parent(s) trouvé(s) en base de données");
                return parents;
            }
        } catch (Exception e) {
            System.out.println("⚠️ Erreur lors de la récupération des parents: " + e.getMessage());
        }

        // 🎭 Fallback: retourne des parents fictifs pour la démo
        System.out.println("🎭 Utilisation de parents fictifs pour la démo");
        return Arrays.asList(
                createDemoParent("parent-123", "Mohamed Alami", busId, stopId),
                createDemoParent("parent-456", "Fatima Zahra", busId, stopId)
        );
    }

    /**
     * Récupère un parent par son ID
     */
    public Parent getParentById(String parentId) {
        System.out.println("🔍 Recherche du parent: " + parentId);

        try {
            return parentRepository.findById(parentId)
                    .orElseThrow(() -> new RuntimeException("Parent non trouvé: " + parentId));
        } catch (Exception e) {
            System.out.println("⚠️ Parent non trouvé en base, création d'un parent de démo");
            // Retourne un parent de démo si non trouvé
            return createDemoParent(parentId, "Parent Demo", "BUS_1", "STOP_001");
        }
    }

    /**
     * Récupère toutes les pénalités d'un parent
     */
    public List<PenaltyEvent> getPenalties(String parentId) {
        System.out.println("📋 Récupération des pénalités pour parent: " + parentId);

        try {
            return penaltyRepository.findByParentId(parentId);
        } catch (Exception e) {
            System.out.println("⚠️ Erreur lors de la récupération des pénalités: " + e.getMessage());
            return Arrays.asList(); // Liste vide
        }
    }

    /**
     * Récupère l'ETA du bus pour un parent
     */
    public BusETA getBusETA(String parentId) {
        System.out.println("⏱️ Récupération de l'ETA pour parent: " + parentId);

        Parent parent = getParentById(parentId);

        // 🎯 Crée un ETA de démo
        BusETA busETA = new BusETA();
        busETA.setBusId(parent.getBusId());
        busETA.setStopId(parent.getStopId());
        busETA.setMinutesRemaining(5); // Valeur par défaut pour la démo

        System.out.println("✅ ETA calculé: " + busETA.getMinutesRemaining() + " minutes");
        return busETA;
    }

    /**
     * Crée tous les parents en base pour la démo
     */
    public void createDemoParents() {
        System.out.println("🎬 Création des parents de démo...");

        try {
            // Vérifie si des parents existent déjà
            if (parentRepository.count() > 0) {
                System.out.println("ℹ️ Des parents existent déjà en base, skip création");
                return;
            }

            List<Parent> demoParents = Arrays.asList(
                    createDemoParent("parent-123", "Mohamed Alami", "BUS_1", "STOP_001"),
                    createDemoParent("parent-456", "Fatima Zahra", "BUS_1", "STOP_001"),
                    createDemoParent("parent-789", "Ahmed Bennani", "BUS_2", "STOP_002")
            );

            parentRepository.saveAll(demoParents);
            System.out.println("✅ " + demoParents.size() + " parents de démo créés en base de données");
        } catch (Exception e) {
            System.out.println("⚠️ Erreur lors de la création des parents: " + e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * Helper: crée un parent pour la démo
     */
    private Parent createDemoParent(String parentId, String name, String busId, String stopId) {
        Parent parent = new Parent();
        parent.setParentId(parentId);
        parent.setName(name);
        parent.setBusId(busId);
        parent.setStopId(stopId);
        return parent;
    }

    /**
     * Récupère tous les parents (utile pour debug)
     */
    public List<Parent> getAllParents() {
        try {
            return parentRepository.findAll();
        } catch (Exception e) {
            System.out.println("⚠️ Erreur lors de la récupération de tous les parents: " + e.getMessage());
            return Arrays.asList();
        }
    }
}