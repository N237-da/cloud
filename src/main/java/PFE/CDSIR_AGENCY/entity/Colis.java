package PFE.CDSIR_AGENCY.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import java.time.LocalDateTime;
import lombok.Generated;

@Entity
@Table(
		name = "colis"
)
public class Colis {
	@Id
	@GeneratedValue(
			strategy = GenerationType.IDENTITY
	)
	@Column(
			name = "id_colis"
	)
	private Long id;
	@ManyToOne(
			fetch = FetchType.LAZY
	)
	@JoinColumn(
			name = "id_agence",
			nullable = false
	)
	private @NotNull(
			message = "L'agence est requise"
	) Agence agence;
	@ManyToOne(
			fetch = FetchType.LAZY
	)
	@JoinColumn(
			name = "id_client_expediteur"
	)
	private Client clientExpediteur;
	@Column(
			name = "description",
			nullable = false
	)
	private @NotBlank(
			message = "La description du colis est requise"
	) String description;
	@Column(
			name = "weight",
			nullable = false
	)
	private @NotNull(
			message = "Le poids du colis est requis"
	) @Positive(
			message = "Le poids doit être un nombre positif"
	) Double weight;
	@Column(
			name = "dimensions"
	)
	private String dimensions;
	@Column(
			name = "estimated_cost",
			nullable = false
	)
	private @NotNull(
			message = "Le coût estimé est requis"
	) @Positive(
			message = "Le coût estimé doit être un nombre positif"
	) Double estimatedCost;
	@Column(
			name = "tracking_number",
			unique = true
	)
	private String trackingNumber;
	@Column(
			name = "date_enregistrement",
			nullable = false
	)
	private LocalDateTime dateEnregistrement;
	@Column(
			name = "date_expedition"
	)
	private LocalDateTime dateExpedition;
	@Column(
			name = "date_arrivee_agence_destination"
	)
	private LocalDateTime dateArriveeAgenceDestination;
	@Column(
			name = "date_livraison_estimee"
	)
	private LocalDateTime dateLivraisonEstimee;
	@Column(
			name = "date_livraison_prevue"
	)
	private LocalDateTime dateLivraisonPrevue;
	@Column(
			name = "date_livraison_reelle"
	)
	private LocalDateTime dateLivraisonReelle;
	@Enumerated(EnumType.STRING)
	@Column(
			name = "statut",
			nullable = false
	)
	private ColisStatus statut;
	@Column(
			name = "nom_expediteur"
	)
	private String nomExpediteur;
	@Column(
			name = "telephone_expediteur"
	)
	private String telephoneExpediteur;
	@Column(
			name = "email_expediteur"
	)
	private String emailExpediteur;
	@Column(
			name = "ville_origine",
			nullable = false
	)
	private String villeOrigine;
	@Column(
			name = "quartier_expedition"
	)
	private String quartierExpedition;
	@Column(
			name = "nom_destinataire",
			nullable = false
	)
	private String nomDestinataire;
	@Column(
			name = "numero_destinataire",
			nullable = false
	)
	private String numeroDestinataire;
	@Column(
			name = "email_destinataire"
	)
	private String emailDestinataire;
	@Column(
			name = "ville_de_destination",
			nullable = false
	)
	private String villeDeDestination;
	@Column(
			name = "quartier_destination"
	)
	private String quartierDestination;

	// --- NOUVEAUX CHAMPS POUR LE PAIEMENT ---
	@Column(name = "mode_paiement")
	private String modePaiement;

	@Column(name = "reference_paiement")
	private String referencePaiement;

	@Column(name = "date_paiement") // Nouveau champ ajouté
	private LocalDateTime datePaiement;
	// --- FIN NOUVEAUX CHAMPS POUR LE PAIEMENT ---

	@Column(name = "code_validation")
	private String codeValidation;

	@ManyToOne(
			fetch = FetchType.LAZY
	)
	@JoinColumn(
			name = "assigned_voyage_id"
	)
	private Voyage assignedVoyage;

	@Generated
	public Colis() {
	}

	@Generated
	public Long getId() {
		return this.id;
	}

	@Generated
	public Agence getAgence() {
		return this.agence;
	}

	@Generated
	public Client getClientExpediteur() {
		return this.clientExpediteur;
	}

	@Generated
	public String getDescription() {
		return this.description;
	}

	@Generated
	public Double getWeight() {
		return this.weight;
	}

	@Generated
	public String getDimensions() {
		return this.dimensions;
	}

	@Generated
	public Double getEstimatedCost() {
		return this.estimatedCost;
	}

	@Generated
	public String getTrackingNumber() {
		return this.trackingNumber;
	}

	@Generated
	public LocalDateTime getDateEnregistrement() {
		return this.dateEnregistrement;
	}

	@Generated
	public LocalDateTime getDateExpedition() {
		return this.dateExpedition;
	}

	@Generated
	public LocalDateTime getDateArriveeAgenceDestination() {
		return this.dateArriveeAgenceDestination;
	}

	@Generated
	public LocalDateTime getDateLivraisonEstimee() {
		return this.dateLivraisonEstimee;
	}

	@Generated
	public LocalDateTime getDateLivraisonPrevue() {
		return this.dateLivraisonPrevue;
	}

	@Generated
	public LocalDateTime getDateLivraisonReelle() {
		return this.dateLivraisonReelle;
	}

	@Generated
	public ColisStatus getStatut() {
		return this.statut;
	}

	@Generated
	public String getNomExpediteur() {
		return this.nomExpediteur;
	}

	@Generated
	public String getTelephoneExpediteur() {
		return this.telephoneExpediteur;
	}

	@Generated
	public String getEmailExpediteur() {
		return this.emailExpediteur;
	}

	@Generated
	public String getVilleOrigine() {
		return this.villeOrigine;
	}

	@Generated
	public String getQuartierExpedition() {
		return this.quartierExpedition;
	}

	@Generated
	public String getNomDestinataire() {
		return this.nomDestinataire;
	}

	@Generated
	public String getNumeroDestinataire() {
		return this.numeroDestinataire;
	}

	@Generated
	public String getEmailDestinataire() {
		return this.getEmailDestinataire();
	}

	@Generated
	public String getVilleDeDestination() {
		return this.villeDeDestination;
	}

	@Generated
	public String getQuartierDestination() {
		return this.quartierDestination;
	}

	// --- GETTERS & SETTERS POUR LE PAIEMENT ---
	@Generated
	public String getModePaiement() {
		return this.modePaiement;
	}

	@Generated
	public String getReferencePaiement() {
		return this.referencePaiement;
	}

	@Generated
	public LocalDateTime getDatePaiement() { // Getter pour le nouveau champ
		return this.datePaiement;
	}

	@Generated
	public void setModePaiement(String modePaiement) {
		this.modePaiement = modePaiement;
	}

	@Generated
	public void setReferencePaiement(String referencePaiement) {
		this.referencePaiement = referencePaiement;
	}

	@Generated
	public void setDatePaiement(LocalDateTime datePaiement) { // Setter pour le nouveau champ
		this.datePaiement = datePaiement;
	}
	// --- FIN GETTERS & SETTERS POUR LE PAIEMENT ---


	@Generated
	public String getCodeValidation() {
		return this.codeValidation;
	}

	@Generated
	public Voyage getAssignedVoyage() {
		return this.assignedVoyage;
	}

	@Generated
	public void setId(Long id) {
		this.id = id;
	}

	@Generated
	public void setAgence(Agence agence) {
		this.agence = agence;
	}

	@Generated
	public void setClientExpediteur(Client clientExpediteur) {
		this.clientExpediteur = clientExpediteur;
	}

	@Generated
	public void setDescription(String description) {
		this.description = description;
	}

	@Generated
	public void setWeight(Double weight) {
		this.weight = weight;
	}

	@Generated
	public void setDimensions(String dimensions) {
		this.dimensions = dimensions;
	}

	@Generated
	public void setEstimatedCost(Double estimatedCost) {
		this.estimatedCost = estimatedCost;
	}

	@Generated
	public void setTrackingNumber(String trackingNumber) {
		this.trackingNumber = trackingNumber;
	}

	@Generated
	public void setDateEnregistrement(LocalDateTime dateEnregistrement) {
		this.dateEnregistrement = dateEnregistrement;
	}

	@Generated
	public void setDateExpedition(LocalDateTime dateExpedition) {
		this.dateExpedition = dateExpedition;
	}

	@Generated
	public void setDateArriveeAgenceDestination(LocalDateTime dateArriveeAgenceDestination) {
		this.dateArriveeAgenceDestination = dateArriveeAgenceDestination;
	}

	@Generated
	public void setDateLivraisonEstimee(LocalDateTime dateLivraisonEstimee) {
		this.dateLivraisonEstimee = dateLivraisonEstimee;
	}

	@Generated
	public void setDateLivraisonPrevue(LocalDateTime dateLivraisonPrevue) {
		this.dateLivraisonPrevue = dateLivraisonPrevue;
	}

	@Generated
	public void setDateLivraisonReelle(LocalDateTime dateLivraisonReelle) {
		this.dateLivraisonReelle = dateLivraisonReelle;
	}

	@Generated
	public void setStatut(ColisStatus statut) {
		this.statut = statut;
	}

	@Generated
	public void setNomExpediteur(String nomExpediteur) {
		this.nomExpediteur = nomExpediteur;
	}

	@Generated
	public void setTelephoneExpediteur(String telephoneExpediteur) {
		this.telephoneExpediteur = telephoneExpediteur;
	}

	@Generated
	public void setEmailExpediteur(String emailExpediteur) {
		this.emailExpediteur = emailExpediteur;
	}

	@Generated
	public void setVilleOrigine(String villeOrigine) {
		this.villeOrigine = villeOrigine;
	}

	@Generated
	public void setQuartierExpedition(String quartierExpedition) {
		this.quartierExpedition = quartierExpedition;
	}

	@Generated
	public void setNomDestinataire(String nomDestinataire) {
		this.nomDestinataire = nomDestinataire;
	}

	@Generated
	public void setNumeroDestinataire(String numeroDestinataire) {
		this.numeroDestinataire = numeroDestinataire;
	}

	@Generated
	public void setEmailDestinataire(String emailDestinataire) {
		this.emailDestinataire = emailDestinataire;
	}

	@Generated
	public void setVilleDeDestination(String villeDeDestination) {
		this.villeDeDestination = villeDeDestination;
	}

	@Generated
	public void setQuartierDestination(String quartierDestination) {
		this.quartierDestination = quartierDestination;
	}

	@Generated
	public void setCodeValidation(String codeValidation) {
		this.codeValidation = codeValidation;
	}

	@Generated
	public void setAssignedVoyage(Voyage assignedVoyage) {
		this.assignedVoyage = assignedVoyage;
	}

	@Generated
	public boolean equals(Object o) {
		if (o == this) {
			return true;
		} else if (!(o instanceof Colis)) {
			return false;
		} else {
			Colis other = (Colis)o;
			if (!other.canEqual(this)) {
				return false;
			} else {
				Object this$id = this.getId();
				Object other$id = other.getId();
				if (this$id == null) {
					if (other$id != null) {
						return false;
					}
				} else if (!this$id.equals(other$id)) {
					return false;
				}

				Object this$agence = this.getAgence();
				Object other$agence = other.getAgence();
				if (this$agence == null) {
					if (other$agence != null) {
						return false;
					}
				} else if (!this$agence.equals(other$agence)) {
					return false;
				}

				Object this$clientExpediteur = this.getClientExpediteur();
				Object other$clientExpediteur = other.getClientExpediteur();
				if (this$clientExpediteur == null) {
					if (other$clientExpediteur != null) {
						return false;
					}
				} else if (!this$clientExpediteur.equals(other$clientExpediteur)) {
					return false;
				}

				label364: {
					Object this$description = this.getDescription();
					Object other$description = other.getDescription();
					if (this$description == null) {
						if (other$description != null) {
							return false;
						}
					} else if (!this$description.equals(other$description)) {
						break label364;
					}

					Object this$weight = this.getWeight();
					Object other$weight = other.getWeight();
					if (this$weight == null) {
						if (other$weight != null) {
							return false;
						}
					} else if (!this$weight.equals(other$weight)) {
						return false;
					}

					label350: {
						Object this$dimensions = this.getDimensions();
						Object other$dimensions = other.getDimensions();
						if (this$dimensions == null) {
							if (other$dimensions != null) {
								return false;
							}
						} else if (!this$dimensions.equals(other$dimensions)) {
							break label350;
						}

						Object this$estimatedCost = this.getEstimatedCost();
						Object other$estimatedCost = other.getEstimatedCost();
						if (this$estimatedCost == null) {
							if (other$estimatedCost != null) {
								return false;
							}
						} else if (!this$estimatedCost.equals(other$estimatedCost)) {
							return false;
						}

						label336: {
							Object this$trackingNumber = this.getTrackingNumber();
							Object other$trackingNumber = other.getTrackingNumber();
							if (this$trackingNumber == null) {
								if (other$trackingNumber != null) {
									return false;
								}
							} else if (!this$trackingNumber.equals(other$trackingNumber)) {
								break label336;
							}

							Object this$dateEnregistrement = this.getDateEnregistrement();
							Object other$dateEnregistrement = other.getDateEnregistrement();
							if (this$dateEnregistrement == null) {
								if (other$dateEnregistrement != null) {
									return false;
								}
							} else if (!this$dateEnregistrement.equals(other$dateEnregistrement)) {
								return false;
							}

							label322: {
								Object this$dateExpedition = this.getDateExpedition();
								Object other$dateExpedition = other.getDateExpedition();
								if (this$dateExpedition == null) {
									if (other$dateExpedition != null) {
										return false;
									}
								} else if (!this$dateExpedition.equals(other$dateExpedition)) {
									break label322;
								}

								Object this$dateArriveeAgenceDestination = this.getDateArriveeAgenceDestination();
								Object other$dateArriveeAgenceDestination = other.getDateArriveeAgenceDestination();
								if (this$dateArriveeAgenceDestination == null) {
									if (other$dateArriveeAgenceDestination != null) {
										return false;
									}
								} else if (!this$dateArriveeAgenceDestination.equals(other$dateArriveeAgenceDestination)) {
									return false;
								}

								label308: {
									Object this$dateLivraisonEstimee = this.getDateLivraisonEstimee();
									Object other$dateLivraisonEstimee = other.getDateLivraisonEstimee();
									if (this$dateLivraisonEstimee == null) {
										if (other$dateLivraisonEstimee != null) {
											return false;
										}
									} else if (!this$dateLivraisonEstimee.equals(other$dateLivraisonEstimee)) {
										break label308;
									}

									Object this$dateLivraisonPrevue = this.getDateLivraisonPrevue();
									Object other$dateLivraisonPrevue = other.getDateLivraisonPrevue();
									if (this$dateLivraisonPrevue == null) {
										if (other$dateLivraisonPrevue != null) {
											return false;
										}
									} else if (!this$dateLivraisonPrevue.equals(other$dateLivraisonPrevue)) {
										return false;
									}

									Object this$dateLivraisonReelle = this.getDateLivraisonReelle();
									Object other$dateLivraisonReelle = other.getDateLivraisonReelle();
									if (this$dateLivraisonReelle == null) {
										if (other$dateLivraisonReelle != null) {
											return false;
										}
									} else if (!this$dateLivraisonReelle.equals(other$dateLivraisonReelle)) {
										return false;
									}

									label287: {
										Object this$statut = this.getStatut();
										Object other$statut = other.getStatut();
										if (this$statut == null) {
											if (other$statut != null) {
												return false;
											}
										} else if (!this$statut.equals(other$statut)) {
											break label287;
										}

										Object this$nomExpediteur = this.getNomExpediteur();
										Object other$nomExpediteur = other.getNomExpediteur();
										if (this$nomExpediteur == null) {
											if (other$nomExpediteur != null) {
												return false;
											}
										} else if (!this$nomExpediteur.equals(other$nomExpediteur)) {
											return false;
										}

										label273: {
											Object this$telephoneExpediteur = this.getTelephoneExpediteur();
											Object other$telephoneExpediteur = other.getTelephoneExpediteur();
											if (this$telephoneExpediteur == null) {
												if (other$telephoneExpediteur != null) {
													return false;
												}
											} else if (!this$telephoneExpediteur.equals(other$telephoneExpediteur)) {
												break label273;
											}

											Object this$emailExpediteur = this.getEmailExpediteur();
											Object other$emailExpediteur = other.getEmailDestinataire();
											if (this$emailExpediteur == null) {
												if (other$emailExpediteur != null) {
													return false;
												}
											} else if (!this$emailExpediteur.equals(other$emailExpediteur)) {
												return false;
											}

											label259: {
												Object this$villeOrigine = this.getVilleOrigine();
												Object other$villeOrigine = other.getVilleOrigine();
												if (this$villeOrigine == null) {
													if (other$villeOrigine != null) {
														return false;
													}
												} else if (!this$villeOrigine.equals(other$villeOrigine)) {
													break label259;
												}

												Object this$quartierExpedition = this.getQuartierExpedition();
												Object other$quartierExpedition = other.getQuartierExpedition();
												if (this$quartierExpedition == null) {
													if (other$quartierExpedition != null) {
														return false;
													}
												} else if (!this$quartierExpedition.equals(other$quartierExpedition)) {
													return false;
												}

												label245: {
													Object this$nomDestinataire = this.getNomDestinataire();
													Object other$nomDestinataire = other.getNomDestinataire();
													if (this$nomDestinataire == null) {
														if (other$nomDestinataire != null) {
															return false;
														}
													} else if (!this$nomDestinataire.equals(other$nomDestinataire)) {
														break label245;
													}

													Object this$numeroDestinataire = this.getNumeroDestinataire();
													Object other$numeroDestinataire = other.getNumeroDestinataire();
													if (this$numeroDestinataire == null) {
														if (other$numeroDestinataire != null) {
															return false;
														}
													} else if (!this$numeroDestinataire.equals(other$numeroDestinataire)) {
														return false;
													}

													label231: {
														Object this$emailDestinataire = this.getEmailDestinataire();
														Object other$emailDestinataire = other.getEmailDestinataire();
														if (this$emailDestinataire == null) {
															if (other$emailDestinataire != null) {
																return false;
															}
														} else if (!this$emailDestinataire.equals(other$emailDestinataire)) {
															break label231;
														}

														Object this$villeDeDestination = this.getVilleDeDestination();
														Object other$villeDeDestination = other.getVilleDeDestination();
														if (this$villeDeDestination == null) {
															if (other$villeDeDestination != null) {
																return false;
															}
														} else if (!this$villeDeDestination.equals(other$villeDeDestination)) {
															return false;
														}

														label217: {
															Object this$quartierDestination = this.getQuartierDestination();
															Object other$quartierDestination = other.getQuartierDestination();
															if (this$quartierDestination == null) {
																if (other$quartierDestination != null) {
																	return false;
																}
															} else if (!this$quartierDestination.equals(other$quartierDestination)) {
																break label217;
															}

															// --- NOUVELLE LOGIQUE POUR LE PAIEMENT ---
															Object this$modePaiement = this.getModePaiement();
															Object other$modePaiement = other.getModePaiement();
															if (this$modePaiement == null) {
																if (other$modePaiement != null) {
																	return false;
																}
															} else if (!this$modePaiement.equals(other$modePaiement)) {
																return false;
															}

															Object this$referencePaiement = this.getReferencePaiement();
															Object other$referencePaiement = other.getReferencePaiement();
															if (this$referencePaiement == null) {
																if (other$referencePaiement != null) {
																	return false;
																}
															} else if (!this$referencePaiement.equals(other$referencePaiement)) {
																return false;
															}
															// Nouveau champ datePaiement dans equals
															Object this$datePaiement = this.getDatePaiement();
															Object other$datePaiement = other.getDatePaiement();
															if (this$datePaiement == null) {
																if (other$datePaiement != null) {
																	return false;
																}
															} else if (!this$datePaiement.equals(other$datePaiement)) {
																return false;
															}
															// --- FIN NOUVELLE LOGIQUE POUR LE PAIEMENT ---

															Object this$codeValidation = this.getCodeValidation();
															Object other$codeValidation = other.getCodeValidation();
															if (this$codeValidation == null) {
																if (other$codeValidation != null) {
																	return false;
																}
															} else if (!this$codeValidation.equals(other$codeValidation)) {
																return false;
															}

															Object this$assignedVoyage = this.getAssignedVoyage();
															Object other$assignedVoyage = other.getAssignedVoyage();
															if (this$assignedVoyage == null) {
																if (other$assignedVoyage != null) {
																	return false;
																}
															} else if (!this$assignedVoyage.equals(other$assignedVoyage)) {
																return false;
															}

															return true;
														}
													}
												}
											}
										}
									}
								}
							}
						}
					}
				}
			}
		}
		return true;
	}

	@Generated
	protected boolean canEqual(Object other) {
		return other instanceof Colis;
	}

	@Generated
	public int hashCode() {
		int PRIME = 59;
		int result = 1;
		Object $id = this.getId();
		result = result * 59 + ($id == null ? 43 : $id.hashCode());
		Object $agence = this.getAgence();
		result = result * 59 + ($agence == null ? 43 : $agence.hashCode());
		Object $clientExpediteur = this.getClientExpediteur();
		result = result * 59 + ($clientExpediteur == null ? 43 : $clientExpediteur.hashCode());
		Object $description = this.getDescription();
		result = result * 59 + ($description == null ? 43 : $description.hashCode());
		Object $weight = this.getWeight();
		result = result * 59 + ($weight == null ? 43 : $weight.hashCode());
		Object $dimensions = this.getDimensions();
		result = result * 59 + ($dimensions == null ? 43 : $dimensions.hashCode());
		Object $estimatedCost = this.getEstimatedCost();
		result = result * 59 + ($estimatedCost == null ? 43 : $estimatedCost.hashCode());
		Object $trackingNumber = this.getTrackingNumber();
		result = result * 59 + ($trackingNumber == null ? 43 : $trackingNumber.hashCode());
		Object $dateEnregistrement = this.getDateEnregistrement();
		result = result * 59 + ($dateEnregistrement == null ? 43 : $dateEnregistrement.hashCode());
		Object $dateExpedition = this.getDateExpedition();
		result = result * 59 + ($dateExpedition == null ? 43 : $dateExpedition.hashCode());
		Object $dateArriveeAgenceDestination = this.getDateArriveeAgenceDestination();
		result = result * 59 + ($dateArriveeAgenceDestination == null ? 43 : $dateArriveeAgenceDestination.hashCode());
		Object $dateLivraisonEstimee = this.getDateLivraisonEstimee();
		result = result * 59 + ($dateLivraisonEstimee == null ? 43 : $dateLivraisonEstimee.hashCode());
		Object $dateLivraisonPrevue = this.getDateLivraisonPrevue();
		result = result * 59 + ($dateLivraisonPrevue == null ? 43 : $dateLivraisonPrevue.hashCode());
		Object $dateLivraisonReelle = this.getDateLivraisonReelle();
		result = result * 59 + ($dateLivraisonReelle == null ? 43 : $dateLivraisonReelle.hashCode());
		Object $statut = this.getStatut();
		result = result * 59 + ($statut == null ? 43 : $statut.hashCode());
		Object $nomExpediteur = this.getNomExpediteur();
		result = result * 59 + ($nomExpediteur == null ? 43 : $nomExpediteur.hashCode());
		Object $telephoneExpediteur = this.getTelephoneExpediteur();
		result = result * 59 + ($telephoneExpediteur == null ? 43 : $telephoneExpediteur.hashCode());
		Object $emailExpediteur = this.getEmailExpediteur();
		result = result * 59 + ($emailExpediteur == null ? 43 : $emailExpediteur.hashCode());
		Object $villeOrigine = this.getVilleOrigine();
		result = result * 59 + ($villeOrigine == null ? 43 : $villeOrigine.hashCode());
		Object $quartierExpedition = this.getQuartierExpedition();
		result = result * 59 + ($quartierExpedition == null ? 43 : $quartierExpedition.hashCode());
		Object $nomDestinataire = this.getNomDestinataire();
		result = result * 59 + ($nomDestinataire == null ? 43 : $nomDestinataire.hashCode());
		Object $numeroDestinataire = this.getNumeroDestinataire();
		result = result * 59 + ($numeroDestinataire == null ? 43 : $numeroDestinataire.hashCode());
		Object $emailDestinataire = this.getEmailDestinataire();
		result = result * 59 + ($emailDestinataire == null ? 43 : $emailDestinataire.hashCode());
		Object $villeDeDestination = this.getVilleDeDestination();
		result = result * 59 + ($villeDeDestination == null ? 43 : $villeDeDestination.hashCode());
		Object $quartierDestination = this.getQuartierDestination();
		result = result * 59 + ($quartierDestination == null ? 43 : $quartierDestination.hashCode());
		// --- NOUVELLE LOGIQUE POUR LE PAIEMENT ---
		Object $modePaiement = this.getModePaiement();
		result = result * 59 + ($modePaiement == null ? 43 : $modePaiement.hashCode());
		Object $referencePaiement = this.getReferencePaiement();
		result = result * 59 + ($referencePaiement == null ? 43 : $referencePaiement.hashCode());
		// Nouveau champ datePaiement dans hashCode
		Object $datePaiement = this.getDatePaiement();
		result = result * 59 + ($datePaiement == null ? 43 : $datePaiement.hashCode());
		// --- FIN NOUVELLE LOGIQUE POUR LE PAIEMENT ---
		Object $codeValidation = this.getCodeValidation();
		result = result * 59 + ($codeValidation == null ? 43 : $codeValidation.hashCode());
		Object $assignedVoyage = this.getAssignedVoyage();
		result = result * 59 + ($assignedVoyage == null ? 43 : $assignedVoyage.hashCode());
		return result;
	}

	@Generated
	public String toString() {
		return "Colis(id=" + this.getId() + ", agence=" + this.getAgence() + ", clientExpediteur=" + this.getClientExpediteur() + ", description=" + this.getDescription() + ", weight=" + this.getWeight() + ", dimensions=" + this.getDimensions() + ", estimatedCost=" + this.getEstimatedCost() + ", trackingNumber=" + this.getTrackingNumber() + ", dateEnregistrement=" + this.getDateEnregistrement() + ", dateExpedition=" + this.getDateExpedition() + ", dateArriveeAgenceDestination=" + this.getDateArriveeAgenceDestination() + ", dateLivraisonEstimee=" + this.getDateLivraisonEstimee() + ", dateLivraisonPrevue=" + this.getDateLivraisonPrevue() + ", dateLivraisonReelle=" + this.getDateLivraisonReelle() + ", statut=" + this.getStatut() + ", nomExpediteur=" + this.getNomExpediteur() + ", telephoneExpediteur=" + this.getTelephoneExpediteur() + ", emailExpediteur=" + this.getEmailExpediteur() + ", villeOrigine=" + this.getVilleOrigine() + ", quartierExpedition=" + this.getQuartierExpedition() + ", nomDestinataire=" + this.getNomDestinataire() + ", numeroDestinataire=" + this.getNumeroDestinataire() + ", emailDestinataire=" + this.getEmailDestinataire() + ", villeDeDestination=" + this.getVilleDeDestination() + ", quartierDestination=" + this.getQuartierDestination() + ", modePaiement=" + this.getModePaiement() + ", referencePaiement=" + this.getReferencePaiement() + ", datePaiement=" + this.getDatePaiement() + ", codeValidation=" + this.getCodeValidation() + ", assignedVoyage=" + this.getAssignedVoyage() + ")";
	}

	public static enum ColisStatus {
		ENREGISTRE("Enregistré"),
		PRET_A_ENVOYER("Prêt à envoyer"),
		EXPEDIE("En transit"),
		ARRIVE_AGENCE_DESTINATION("Arrivé à l'agence de destination"),
		PRET_A_ETRE_RETIRE("Prêt à être retiré"),
		LIVRE("Livré"),
		ANNULE("Annulé"),
		// --- NOUVEAUX STATUTS POUR LE PAIEMENT --
		EN_ATTENTE_PAIEMENT("En attente de paiement"),
		PAYE("Payé");
		// --- FIN NOUVEAUX STATUTS POUR LE PAIEMENT ---


		private final String displayValue;

		@Generated
		private ColisStatus(String displayValue) {
			this.displayValue = displayValue;
		}

		@Generated
		public String getDisplayValue() {
			return this.displayValue;
		}
	}
}