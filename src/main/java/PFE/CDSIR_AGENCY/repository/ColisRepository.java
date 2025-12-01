package PFE.CDSIR_AGENCY.repository;

import PFE.CDSIR_AGENCY.entity.Colis;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.time.LocalDateTime;

@Repository
public interface ColisRepository extends JpaRepository<Colis, Long> {
	// Méthodes existantes
	Optional<Colis> findByTrackingNumber(String trackingNumber);
	List<Colis> findByStatut(Colis.ColisStatus statut);
	List<Colis> findByAssignedVoyageId(Long voyageId);
	List<Colis> findByAgenceId(Long agenceId);
	List<Colis> findByClientExpediteurId(Long clientExpediteurId);
	List<Colis> findByEmailExpediteur(String emailExpediteur);
	List<Colis> findByTelephoneExpediteur(String telephoneExpediteur);
	List<Colis> findByNumeroDestinataireOrEmailDestinataire(String numeroDestinataire, String emailDestinataire);
	List<Colis> findByReferencePaiement(String referencePaiement);
	Optional<Colis> findByIdAndClientExpediteurId(Long id, Long clientExpediteurId);

	// Méthode exists ajoutée
	boolean existsByIdAndClientExpediteurId(Long id, Long clientExpediteurId);

	// Version alternative avec @Query
	@Query("SELECT CASE WHEN COUNT(c) > 0 THEN true ELSE false END FROM Colis c WHERE c.id = :id AND c.clientExpediteur.id = :clientId")
	boolean existsByIdAndClientId(@Param("id") Long id, @Param("clientId") Long clientId);

	// Méthodes pour les destinataires
	List<Colis> findByClientDestinataireId(Long clientDestinataireId);
	@Query("SELECT c FROM Colis c WHERE c.clientDestinataire.id = :clientDestId")
	List<Colis> findByClientDestinataire(@Param("clientDestId") Long clientDestId);

	// Méthode de recherche
	@Query("SELECT c FROM Colis c WHERE " +
			"LOWER(c.trackingNumber) LIKE LOWER(concat('%', :keyword, '%')) OR " +
			"LOWER(c.nomDestinataire) LIKE LOWER(concat('%', :keyword, '%')) OR " +
			"LOWER(c.nomExpediteur) LIKE LOWER(concat('%', :keyword, '%')) OR " +
			"LOWER(c.referencePaiement) LIKE LOWER(concat('%', :keyword, '%'))")
	List<Colis> searchByTrackingNumberOrNames(@Param("keyword") String keyword);

	// Méthodes pour statistiques
	@Query("SELECT COUNT(c) FROM Colis c WHERE c.agence.id = :agenceId AND c.statut = :statut")
	Long countByAgenceIdAndStatut(@Param("agenceId") Long agenceId, @Param("statut") Colis.ColisStatus statut);

	// Méthode pour recherche par date
	@Query("SELECT c FROM Colis c WHERE c.dateEnregistrement BETWEEN :startDate AND :endDate")
	List<Colis> findByDateRange(@Param("startDate") LocalDateTime startDate,
								@Param("endDate") LocalDateTime endDate);
}