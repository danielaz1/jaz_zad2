package repository;

import domain.ApplicationUser;
import domain.UserDetails;

import java.util.List;

public interface ApplicationUserRepository {

	ApplicationUser findByUsername(String username);

	void add(ApplicationUser user);

	List<UserDetails> getAll();

	void addAdmin();

	void updateToPremium(String username);

	void removePremium(String username);
}
