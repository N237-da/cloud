package PFE.CDSIR_AGENCY.service;

import PFE.CDSIR_AGENCY.dto.ColisRequestDto;
import PFE.CDSIR_AGENCY.dto.ColisResponseDto;
import PFE.CDSIR_AGENCY.exception.OperationNotAllowedException;
import java.util.List;

public interface ColisService {
	ColisResponseDto createColis(ColisRequestDto colisRequestDto);
	ColisResponseDto createColisForClient(Long clientId, ColisRequestDto colisRequestDto);
	ColisResponseDto getColisById(Long id);
	ColisResponseDto getColisByIdAndClientId(Long colisId, Long clientId);
	List<ColisResponseDto> getAllColis();
	List<ColisResponseDto> getColisBySenderId(Long senderId);
	List<ColisResponseDto> getColisByRecipientId(Long recipientId);
	ColisResponseDto updateColis(Long id, ColisRequestDto colisRequestDto);
	ColisResponseDto updateColisForClient(Long colisId, Long clientId, ColisRequestDto colisRequestDto);
	void deleteColis(Long id);
	void cancelColisForClient(Long colisId, Long clientId);
	ColisResponseDto getColisByTrackingNumber(String trackingNumber);
	ColisResponseDto assignColisToVoyage(Long colisId, Long voyageId);
	ColisResponseDto updateColisStatus(Long colisId, String newStatus);
	void sendSenderPickupNotification(Long colisId);
	void sendRecipientReadyForPickupNotification(Long colisId);
	void sendDeliveryConfirmationNotification(Long colisId);
	List<ColisResponseDto> getColisByStatus(String status);
	List<ColisResponseDto> getColisByVoyageId(Long voyageId);
	void verifyColisOwnership(Long colisId, Long clientId) throws OperationNotAllowedException;
	List<ColisResponseDto> searchColis(String keyword);
	ColisResponseDto updateColisDeliveryDate(Long colisId, String newDeliveryDate);
	ColisResponseDto confirmColisPayment(String paymentReference);
}