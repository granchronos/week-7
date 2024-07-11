package pe.edu.idat.week_6.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import pe.edu.idat.week_6.model.Customer;
import pe.edu.idat.week_6.repository.CustomerRepository;
import pe.edu.idat.week_6.service.CustomerService;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    @Override
    public void add(Customer customer) {
        customer.setPassword(passwordEncoder.encode(customer.getPassword()));
        customer.setRoles(Set.of("ROLE_USER"));
        customerRepository.save(customer);
    }

    @Override
    public List<Customer> all() {
        return customerRepository.findAll();
    }

    @Override
    public Optional<Customer> get(Long id) {
        return customerRepository.findById(id);
    }

    @Override
    public void edit(Customer customer) {
        customerRepository.save(customer);
    }

    @Override
    public void delete(Long id) {
        customerRepository.deleteById(id);
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        var customer = customerRepository.findCustomerByEmail(email).orElse(null);
        if (customer == null) {
            throw new UsernameNotFoundException("No user found with email");
        }
        return User.builder()
                .username(customer.getEmail())
                .password(customer.getPassword())
                .authorities(mapRolesToAuthorities(customer.getRoles()))
                .build();
    }

    private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<String> roles) {
        return roles.stream()
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
    }
}
