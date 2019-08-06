package intiveFDV.services;

import intiveFDV.domain.Promocion;
import intiveFDV.domain.PromocionType;

public interface PromocionService {

	Promocion getPromocion(PromocionType promocionType);

}
