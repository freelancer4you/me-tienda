package de.goldmann.tienda.controller;

import java.util.Objects;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;

import de.goldmann.tienda.dao.AccountRepository;
import de.goldmann.tienda.domain.Account;
import de.goldmann.tienda.dto.AccountDTO;


@RestController
@Transactional
public class AccountController {

    private static final Logger           LOGGER = LoggerFactory.getLogger(AccountController.class);

    private final AccountRepository accountRepository;

    @Autowired
    public AccountController(final AccountRepository accountRepository) {
        this.accountRepository = Objects
                .requireNonNull(accountRepository, "Parameter 'accountRepository' darf nicht null sein.");
    }

    @RequestMapping(value = "/api/resources/userregistered", method = RequestMethod.GET)
    public boolean userRegistered(@RequestParam("email") final String email) throws Exception {
        return accountRepository.findByEmail(email).isPresent();
    }

    @ResponseBody
    @RequestMapping(value = "/api/resources/defaultregistration", method = RequestMethod.POST)
    public ResponseEntity<String> googleRegistration(@RequestBody final String payload) throws Exception {

        final ObjectMapper mapper = new ObjectMapper();
        try
        {
            final AccountDTO acc = mapper.readValue(payload, AccountDTO.class);

            final String email = acc.getEmail();
            if (accountRepository.findByEmail(email).isPresent())
            {
                return ResponseEntity.status(HttpStatus.CONFLICT).body(
                        "{\"message\":\" Es existiert bereits ein Nutzer mit der Email-Adresse '" + email + "'.\"}");
            }
            accountRepository.save(new Account(acc));
        }
        catch (final Exception e)
        {
            LOGGER.error("Fehler bei Benutzerregistrierung:" + payload, e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("");
        }
        return ResponseEntity.ok().body("");
    }
}
