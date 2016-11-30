package fr.jkh.iamcore.service.matching.impl;

import fr.jkh.iamcore.datamodel.Identity;
import fr.jkh.iamcore.service.matching.Match;

public class ContainsMatch implements Match<Identity> {
	
	
	@Override
	public boolean match(Identity firstIdentity, Identity secondIdentity) {
		return firstIdentity.getDisplayName().contains(secondIdentity.getDisplayName());
	}

}
