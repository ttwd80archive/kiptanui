package com.twistlet.kiptanui.security.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.transaction.annotation.Transactional;

import com.twistlet.kiptanui.model.entity.Role;
import com.twistlet.kiptanui.model.entity.User;
import com.twistlet.kiptanui.model.entity.UserRole;
import com.twistlet.kiptanui.model.repository.UserRepository;
import com.twistlet.kiptanui.model.repository.UserRoleRepository;

public class UserDetailsServiceImpl implements UserDetailsService {

	private final UserRepository userRepository;
	private final UserRoleRepository userRoleRepository;

	@Autowired
	public UserDetailsServiceImpl(UserRepository userRepository, UserRoleRepository userRoleRepository) {
		this.userRepository = userRepository;
		this.userRoleRepository = userRoleRepository;
	}

	@Override
	@Transactional(readOnly = true)
	public UserDetails loadUserByUsername(final String username) throws UsernameNotFoundException {
		final User user = userRepository.findOne(username);
		if (user == null) {
			throw new UsernameNotFoundException("Username " + username + " not found");
		}
		final List<UserRole> list = userRoleRepository.findByUserId(username);
		final List<GrantedAuthority> authorities = new ArrayList<>();
		for (final UserRole userRole : list) {
			final Role role = userRole.getRole();
			final GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(role.getRoleName());
			authorities.add(grantedAuthority);
		}
		return new InternalUser(username, user.getName(), user.getPassword(), authorities);
	}

	public class InternalUser extends org.springframework.security.core.userdetails.User {
		private static final long serialVersionUID = -4446704583952926077L;
		private final String name;

		public InternalUser(final String username, final String name, final String password,
				final Collection<? extends GrantedAuthority> authorities) {
			super(username, password, authorities);
			this.name = name;
		}

		public String getName() {
			return name;
		}

	}
}
