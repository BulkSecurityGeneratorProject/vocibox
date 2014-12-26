package vocibox.web.rest;

import com.codahale.metrics.annotation.Timed;
import vocibox.domain.Expression;
import vocibox.repository.ExpressionRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing Expression.
 */
@RestController
@RequestMapping("/app")
public class ExpressionResource {

    private final Logger log = LoggerFactory.getLogger(ExpressionResource.class);

    @Inject
    private ExpressionRepository expressionRepository;

    /**
     * POST  /rest/expressions -> Create a new expression.
     */
    @RequestMapping(value = "/rest/expressions",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public void create(@RequestBody Expression expression) {
        log.debug("REST request to save Expression : {}", expression);
        expressionRepository.save(expression);
    }

    /**
     * GET  /rest/expressions -> get all the expressions.
     */
    @RequestMapping(value = "/rest/expressions",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public List<Expression> getAll() {
        log.debug("REST request to get all Expressions");
        return expressionRepository.findAll();
    }

    /**
     * GET  /rest/expressions/:id -> get the "id" expression.
     */
    @RequestMapping(value = "/rest/expressions/{id}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<Expression> get(@PathVariable Long id) {
        log.debug("REST request to get Expression : {}", id);
        return Optional.ofNullable(expressionRepository.findOne(id))
            .map(expression -> new ResponseEntity<>(
                expression,
                HttpStatus.OK))
            .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    /**
     * DELETE  /rest/expressions/:id -> delete the "id" expression.
     */
    @RequestMapping(value = "/rest/expressions/{id}",
            method = RequestMethod.DELETE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public void delete(@PathVariable Long id) {
        log.debug("REST request to delete Expression : {}", id);
        expressionRepository.delete(id);
    }
}