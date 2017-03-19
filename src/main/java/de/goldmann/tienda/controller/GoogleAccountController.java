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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;

import de.goldmann.tienda.dao.GoogleAccountRepository;
import de.goldmann.tienda.dto.GoogleAccountDTO;


@RestController
@Transactional
public class GoogleAccountController {

    private static final Logger           LOGGER = LoggerFactory.getLogger(GoogleAccountController.class);

    private final GoogleAccountRepository accountRepository;

    @Autowired
    public GoogleAccountController(final GoogleAccountRepository accountRepository) {
        this.accountRepository = Objects
                .requireNonNull(accountRepository, "Parameter 'accountRepository' darf nicht null sein.");
    }

    @ResponseBody
    @RequestMapping(value = "/api/resources/googleregistration", method = RequestMethod.POST)
    public ResponseEntity<String> googleRegistration(@RequestBody final String payload) throws Exception {

        final ObjectMapper mapper = new ObjectMapper();
        // TODO store user in keycloak
        try
        {
            final GoogleAccountDTO acc = mapper.readValue(payload, GoogleAccountDTO.class);

            final String email = acc.getEmail();
            if (accountRepository.findByEmail(email) != null)
            {
                return ResponseEntity.status(HttpStatus.CONFLICT).body(
                        "{\"message\":\" Es existiert bereits ein Nutzer mit der Email-Adresse '" + email + "'.\"}");
            }
        }
        catch (final Exception e)
        {
            LOGGER.error("Fehler bei Benutzerregistrierung:" + payload, e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("");
        }
        return ResponseEntity.ok().body("");
    }
}
