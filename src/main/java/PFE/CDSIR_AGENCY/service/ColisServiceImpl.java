package PFE.CDSIR_AGENCY.service.impl;

import PFE.CDSIR_AGENCY.dto.ColisRequestDto;
import PFE.CDSIR_AGENCY.dto.ColisResponseDto;
import PFE.CDSIR_AGENCY.entity.*;
import PFE.CDSIR_AGENCY.entity.Colis.ColisStatus;
import PFE.CDSIR_AGENCY.exception.ColisNotFoundException;
import PFE.CDSIR_AGENCY.exception.OperationNotAllowedException;
import PFE.CDSIR_AGENCY.repository.*;
import PFE.CDSIR_AGENCY.service.ColisService;
import PFE.CDSIR_AGENCY.util.TrackingNumberGenerator;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class ColisServiceImpl implements ColisService {

    private final ColisRepository colisRepository;
    private final AgenceRepository agenceRepository;
    private final ClientRepository clientRepository;
    private final VoyageRepository voyageRepository;
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    public ColisServiceImpl(ColisRepository colisRepository,
                            AgenceRepository agenceRepository,
                            ClientRepository clientRepository,
                            VoyageRepository voyageRepository) {
        this.colisRepository = colisRepository;
        this.agenceRepository = agenceRepository;
        this.clientRepository = clientRepository;
        this.voyageRepository = voyageRepository;
    }

    // Méthodes principales
    @Override
    @Transactional
    public ColisResponseDto createColis(ColisRequestDto colisRequestDto) {
        Colis colis = mapRequestDtoToEntity(colisRequestDto);
        colis.setTrackingNumber(TrackingNumberGenerator.generateTrackingNumber());
        colis.setDateEnregistrement(LocalDateTime.now());
        colis.setStatut(ColisStatus.ENREGISTRE);
        Colis savedColis = colisRepository.save(colis);
        return convertToDto(savedColis);
    }

    @Override
    @Transactional
    public ColisResponseDto createColisForClient(Long clientId, ColisRequestDto colisRequestDto) {
        Client client = clientRepository.findById(clientId)
                .orElseThrow(() -> new ColisNotFoundException("Client non trouvé avec l'ID: " + clientId));
        Colis colis = mapRequestDtoToEntity(colisRequestDto);
        colis.setClientExpediteur(client);
        colis.setTrackingNumber(TrackingNumberGenerator.generateTrackingNumber());
        colis.setDateEnregistrement(LocalDateTime.now());
        colis.setStatut(ColisStatus.ENREGISTRE);
        Colis savedColis = colisRepository.save(colis);
        return convertToDto(savedColis);
    }

    @Override
    public ColisResponseDto getColisById(Long id) {
        Colis colis = colisRepository.findById(id)
                .orElseThrow(() -> new ColisNotFoundException("Colis non trouvé avec l'ID: " + id));
        return convertToDto(colis);
    }

    @Override
    public ColisResponseDto getColisByIdAndClientId(Long colisId, Long clientId) {
        Colis colis = colisRepository.findByIdAndClientExpediteurId(colisId, clientId)
                .orElseThrow(() -> new ColisNotFoundException("Colis non trouvé pour le client ID: " + clientId));
        return convertToDto(colis);
    }

    @Override
    public List<ColisResponseDto> getAllColis() {
        List<Colis> colisList = colisRepository.findAll();
        if (colisList.isEmpty()) {
            throw new ColisNotFoundException("Aucun colis trouvé dans la base de données");
        }
        return colisList.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<ColisResponseDto> getColisBySenderId(Long senderId) {
        List<Colis> colisList = colisRepository.findByClientExpediteurId(senderId);
        if (colisList.isEmpty()) {
            throw new ColisNotFoundException("Aucun colis trouvé pour l'expéditeur ID: " + senderId);
        }
        return colisList.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<ColisResponseDto> getColisByRecipientId(Long recipientId) {
        List<Colis> colisList = colisRepository.findByClientDestinataireId(recipientId);
        if (colisList.isEmpty()) {
            throw new ColisNotFoundException("Aucun colis trouvé pour le destinataire ID: " + recipientId);
        }
        return colisList.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public ColisResponseDto updateColis(Long id, ColisRequestDto colisRequestDto) {
        Colis existingColis = colisRepository.findById(id)
                .orElseThrow(() -> new ColisNotFoundException("Colis non trouvé avec l'ID: " + id));
        updateColisFromDto(existingColis, colisRequestDto);
        Colis updatedColis = colisRepository.save(existingColis);
        return convertToDto(updatedColis);
    }

    @Override
    @Transactional
    public ColisResponseDto updateColisForClient(Long colisId, Long clientId, ColisRequestDto colisRequestDto) {
        Colis existingColis = colisRepository.findByIdAndClientExpediteurId(colisId, clientId)
                .orElseThrow(() -> new ColisNotFoundException("Colis non trouvé pour le client ID: " + clientId));
        updateColisFromDto(existingColis, colisRequestDto);
        Colis updatedColis = colisRepository.save(existingColis);
        return convertToDto(updatedColis);
    }

    @Override
    @Transactional
    public void deleteColis(Long id) {
        if (!colisRepository.existsById(id)) {
            throw new ColisNotFoundException("Colis non trouvé avec l'ID: " + id);
        }
        colisRepository.deleteById(id);
    }

    @Override
    @Transactional
    public void cancelColisForClient(Long colisId, Long clientId) {
        Colis colis = colisRepository.findByIdAndClientExpediteurId(colisId, clientId)
                .orElseThrow(() -> new ColisNotFoundException("Colis non trouvé pour le client ID: " + clientId));
        colis.setStatut(ColisStatus.ANNULE);
        colisRepository.save(colis);
    }

    @Override
    public ColisResponseDto getColisByTrackingNumber(String trackingNumber) {
        Colis colis = colisRepository.findByTrackingNumber(trackingNumber)
                .orElseThrow(() -> new ColisNotFoundException("Colis non trouvé avec le numéro de suivi: " + trackingNumber));
        return convertToDto(colis);
    }

    @Override
    @Transactional
    public ColisResponseDto assignColisToVoyage(Long colisId, Long voyageId) {
        Colis colis = colisRepository.findById(colisId)
                .orElseThrow(() -> new ColisNotFoundException("Colis non trouvé avec l'ID: " + colisId));
        Voyage voyage = voyageRepository.findById(voyageId)
                .orElseThrow(() -> new ColisNotFoundException("Voyage non trouvé avec l'ID: " + voyageId));
        colis.setAssignedVoyage(voyage);
        colis.setStatut(ColisStatus.EXPEDIE);
        Colis updatedColis = colisRepository.save(colis);
        return convertToDto(updatedColis);
    }

    @Override
    @Transactional
    public ColisResponseDto updateColisStatus(Long colisId, String newStatus) {
        Colis colis = colisRepository.findById(colisId)
                .orElseThrow(() -> new ColisNotFoundException("Colis non trouvé avec l'ID: " + colisId));
        colis.setStatut(ColisStatus.valueOf(newStatus));
        Colis updatedColis = colisRepository.save(colis);
        return convertToDto(updatedColis);
    }

    @Override
    public List<ColisResponseDto> getColisByStatus(String status) {
        List<Colis> colisList = colisRepository.findByStatut(ColisStatus.valueOf(status));
        if (colisList.isEmpty()) {
            throw new ColisNotFoundException("Aucun colis trouvé avec le statut: " + status);
        }
        return colisList.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<ColisResponseDto> getColisByVoyageId(Long voyageId) {
        List<Colis> colisList = colisRepository.findByAssignedVoyageId(voyageId);
        if (colisList.isEmpty()) {
            throw new ColisNotFoundException("Aucun colis trouvé pour le voyage ID: " + voyageId);
        }
        return colisList.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @Override
    public void verifyColisOwnership(Long colisId, Long clientId) throws OperationNotAllowedException {
        if (!colisRepository.existsByIdAndClientExpediteurId(colisId, clientId)) {
            throw new OperationNotAllowedException("Le client ID " + clientId + " ne possède pas le colis ID " + colisId);
        }
    }

    @Override
    public List<ColisResponseDto> searchColis(String keyword) {
        List<Colis> colisList = colisRepository.searchByTrackingNumberOrNames(keyword);
        if (colisList.isEmpty()) {
            throw new ColisNotFoundException("Aucun colis trouvé avec le critère: " + keyword);
        }
        return colisList.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public ColisResponseDto updateColisDeliveryDate(Long colisId, String newDeliveryDate) {
        Colis colis = colisRepository.findById(colisId)
                .orElseThrow(() -> new ColisNotFoundException("Colis non trouvé avec l'ID: " + colisId));

        try {
            LocalDateTime deliveryDate = LocalDateTime.parse(newDeliveryDate, DATE_FORMATTER);
            colis.setDateLivraisonPrevue(deliveryDate);
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException("Format de date invalide. Utilisez le format: yyyy-MM-dd HH:mm");
        }

        Colis updatedColis = colisRepository.save(colis);
        return convertToDto(updatedColis);
    }

    @Override
    @Transactional
    public ColisResponseDto confirmColisPayment(String paymentReference) {
        // Corrected line 243 (or near there, depending on exact formatting)
        // Assuming findByReferencePaiement returns List<Colis> and we want the first one
        Colis colis = colisRepository.findByReferencePaiement(paymentReference).stream()
                .findFirst() // Get an Optional<Colis> from the list
                .orElseThrow(() -> new ColisNotFoundException("Colis non trouvé avec la référence de paiement: " + paymentReference));
        colis.setStatut(ColisStatus.PAYE);
        colis.setDatePaiement(LocalDateTime.now());
        Colis updatedColis = colisRepository.save(colis);
        return convertToDto(updatedColis);
    }

    // Méthodes de notification
    @Override
    public void sendSenderPickupNotification(Long colisId) {
        // Implémentation à compléter
        System.out.println("Notification envoyée à l'expéditeur pour le colis ID: " + colisId);
    }

    @Override
    public void sendRecipientReadyForPickupNotification(Long colisId) {
        // Implémentation à compléter
        System.out.println("Notification envoyée au destinataire pour le colis ID: " + colisId);
    }

    @Override
    public void sendDeliveryConfirmationNotification(Long colisId) {
        // Implémentation à compléter
        System.out.println("Confirmation de livraison envoyée pour le colis ID: " + colisId);
    }

    // Méthodes utilitaires
    private Colis mapRequestDtoToEntity(ColisRequestDto dto) {
        Colis colis = new Colis();
        colis.setDescription(dto.getDescription());
        colis.setWeight(dto.getWeight());
        colis.setDimensions(dto.getDimensions());
        colis.setEstimatedCost(dto.getEstimatedCost());
        colis.setNomExpediteur(dto.getSenderName());
        colis.setTelephoneExpediteur(dto.getSenderPhone());
        colis.setEmailExpediteur(dto.getSenderEmail());
        colis.setVilleOrigine(dto.getOriginCity());
        colis.setQuartierExpedition(dto.getOriginNeighborhood());
        colis.setNomDestinataire(dto.getReceiverName());
        colis.setNumeroDestinataire(dto.getReceiverPhone());
        colis.setEmailDestinataire(dto.getReceiverEmail());
        colis.setVilleDeDestination(dto.getDestinationCity());
        colis.setQuartierDestination(dto.getDestinationNeighborhood());
        colis.setModePaiement(dto.getModePaiement());
        colis.setReferencePaiement(dto.getPaymentReference());

        if (dto.getAgenceId() != null) {
            Agence agence = agenceRepository.findById(dto.getAgenceId())
                    .orElseThrow(() -> new ColisNotFoundException("Agence non trouvée avec l'ID: " + dto.getAgenceId()));
            colis.setAgence(agence);
        }

        return colis;
    }

    private void updateColisFromDto(Colis colis, ColisRequestDto dto) {
        if (dto.getDescription() != null) colis.setDescription(dto.getDescription());
        if (dto.getWeight() != null) colis.setWeight(dto.getWeight());
        if (dto.getDimensions() != null) colis.setDimensions(dto.getDimensions());
        if (dto.getEstimatedCost() != null) colis.setEstimatedCost(dto.getEstimatedCost());
        if (dto.getSenderName() != null) colis.setNomExpediteur(dto.getSenderName());
        if (dto.getSenderPhone() != null) colis.setTelephoneExpediteur(dto.getSenderPhone());
        if (dto.getSenderEmail() != null) colis.setEmailExpediteur(dto.getSenderEmail());
        if (dto.getOriginCity() != null) colis.setVilleOrigine(dto.getOriginCity());
        if (dto.getOriginNeighborhood() != null) colis.setQuartierExpedition(dto.getOriginNeighborhood());
        if (dto.getReceiverName() != null) colis.setNomDestinataire(dto.getReceiverName());
        if (dto.getReceiverPhone() != null) colis.setNumeroDestinataire(dto.getReceiverPhone());
        if (dto.getReceiverEmail() != null) colis.setEmailDestinataire(dto.getReceiverEmail());
        if (dto.getDestinationCity() != null) colis.setVilleDeDestination(dto.getDestinationCity());
        if (dto.getDestinationNeighborhood() != null) colis.setQuartierDestination(dto.getDestinationNeighborhood());
        if (dto.getModePaiement() != null) colis.setModePaiement(dto.getModePaiement());
        if (dto.getPaymentReference() != null) colis.setReferencePaiement(dto.getPaymentReference());
    }

    private ColisResponseDto convertToDto(Colis colis) {
        ColisResponseDto dto = new ColisResponseDto();
        dto.setIdColis(colis.getId());
        dto.setDescription(colis.getDescription());
        dto.setWeight(colis.getWeight());
        dto.setDimensions(colis.getDimensions());
        dto.setEstimatedCost(colis.getEstimatedCost());
        dto.setTrackingNumber(colis.getTrackingNumber());
        dto.setStatut(colis.getStatut());
        dto.setDateEnregistrement(colis.getDateEnregistrement());
        dto.setDateExpedition(colis.getDateExpedition());
        dto.setDateArriveeAgenceDestination(colis.getDateArriveeAgenceDestination());
        // Ligne corrigée : Passe directement le LocalDateTime
        dto.setDateLivraisonPrevue(colis.getDateLivraisonPrevue());
        dto.setDateLivraisonReelle(colis.getDateLivraisonReelle());
        dto.setSenderName(colis.getNomExpediteur());
        dto.setSenderPhone(colis.getTelephoneExpediteur());
        dto.setSenderEmail(colis.getEmailExpediteur());
        dto.setVilleOrigine(colis.getVilleOrigine());
        dto.setQuartierExpedition(colis.getQuartierExpedition());
        dto.setNomDestinataire(colis.getNomDestinataire());
        dto.setNumeroDestinataire(colis.getNumeroDestinataire());
        dto.setEmailDestinataire(colis.getEmailDestinataire());
        dto.setVilleDeDestination(colis.getVilleDeDestination());
        dto.setQuartierDestination(colis.getQuartierDestination());
        dto.setModePaiement(colis.getModePaiement());
        dto.setPaymentReference(colis.getReferencePaiement());
        dto.setCodeValidation(colis.getCodeValidation());

        if (colis.getAgence() != null) {
            dto.setAgenceId(colis.getAgence().getId());
            dto.setAgenceNom(colis.getAgence().getNomAgence());
        }

        if (colis.getAssignedVoyage() != null) {
            dto.setAssignedVoyageId(colis.getAssignedVoyage().getId());
            dto.setAssignedVoyageDescription(colis.getAssignedVoyage().getDescription());
            if (colis.getAssignedVoyage().getVehicule() != null) {
                dto.setAssignedVoyageVehiculeImmatriculation(colis.getAssignedVoyage().getVehicule().getImmatriculation());
            }
        }

        return dto;
    }
}