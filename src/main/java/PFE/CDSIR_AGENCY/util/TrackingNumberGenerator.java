package PFE.CDSIR_AGENCY.util;

import java.util.UUID;

public class TrackingNumberGenerator {
    public static String generateTrackingNumber() {
        // Exemple : génère un numéro de suivi unique (ex: "TRK-AB12CD34")
        return "TRK-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase();
    }
}