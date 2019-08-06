package intiveFDV.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import intiveFDV.domain.Promocion;
import intiveFDV.domain.PromocionType;
import intiveFDV.repositories.PromocionRepository;

@Service
public class PromocionServiceImpl implements PromocionService {

	@Autowired
	private PromocionRepository promocionRepository;
	
	@Override
	public Promocion getPromocion(PromocionType promocionType) {
		return promocionRepository.findByType(promocionType);
	}

}
