package vocibox.web.rest;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

import vocibox.Application;
import vocibox.domain.Expression;
import vocibox.domain.Tag;
import vocibox.repository.ExpressionRepository;
import vocibox.repository.TagRepository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Test class for the ExpressionResource REST controller.
 *
 * @see ExpressionResource
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
public class ExpressionResourceTest {
   private static final DateTimeFormatter dateTimeFormatter = DateTimeFormat.forPattern("yyyy-MM-dd'T'HH:mm:ss");

    private static final String DEFAULT_EXPRESSION = "SAMPLE_TEXT";
    private static final String UPDATED_EXPRESSION = "UPDATED_TEXT";

    private static final String DEFAULT_TRANSLATION = "SAMPLE_TEXT";
    private static final String UPDATED_TRANSLATION = "UPDATED_TEXT";

    private static final Boolean DEFAULT_MASCULINE = false;
    private static final Boolean UPDATED_MASCULINE = true;
    private static final Boolean DEFAULT_FEMININE = false;
    private static final Boolean UPDATED_FEMININE = true;
    private static final Boolean DEFAULT_SINGULAR = false;
    private static final Boolean UPDATED_SINGULAR = true;
    private static final Boolean DEFAULT_PLURAL = false;
    private static final Boolean UPDATED_PLURAL = true;
    private static final String DEFAULT_EXAMPLE = "SAMPLE_TEXT";
    private static final String UPDATED_EXAMPLE = "UPDATED_TEXT";

    private static final String DEFAULT_DEFINITION = "SAMPLE_TEXT";
    private static final String UPDATED_DEFINITION = "UPDATED_TEXT";

    private static final String DEFAULT_OPPOSITE = "SAMPLE_TEXT";
    private static final String UPDATED_OPPOSITE = "UPDATED_TEXT";

    private static final String DEFAULT_COMMENT = "SAMPLE_TEXT";
    private static final String UPDATED_COMMENT = "UPDATED_TEXT";

    private static final String DEFAULT_PRONUNCIATION = "SAMPLE_TEXT";
    private static final String UPDATED_PRONUNCIATION = "UPDATED_TEXT";

    private static final Boolean DEFAULT_IMAGE = false;
    private static final Boolean UPDATED_IMAGE = true;
    private static final BigDecimal DEFAULT_LATITUDE = BigDecimal.ZERO;
    private static final BigDecimal UPDATED_LATITUDE = BigDecimal.ONE;

    private static final BigDecimal DEFAULT_LONGITUDE = BigDecimal.ZERO;
    private static final BigDecimal UPDATED_LONGITUDE = BigDecimal.ONE;

    private static final Integer DEFAULT_PRIORITY = 1;
    private static final Integer UPDATED_PRIORITY = 2;

    private static final Boolean DEFAULT_MARKED = false;
    private static final Boolean UPDATED_MARKED = true;

   private static final DateTime DEFAULT_CREATED_DATE = new DateTime(0L);
   private static final DateTime UPDATED_CREATED_DATE = new DateTime().withMillisOfSecond(0);
   private static final String DEFAULT_CREATED_DATE_STR = dateTimeFormatter.print(DEFAULT_CREATED_DATE);

   private static final DateTime DEFAULT_LAST_MODIFIED_DATE = new DateTime(0L);
   private static final DateTime UPDATED_LAST_MODIFIED_DATE = new DateTime().withMillisOfSecond(0);
   private static final String DEFAULT_MODIFIED_STR = dateTimeFormatter.print(DEFAULT_LAST_MODIFIED_DATE);


    @Inject
    private ExpressionRepository expressionRepository;

    @Inject
    private TagRepository tagRepository;

    private MockMvc restExpressionMockMvc;

    private Expression expression;

    @PostConstruct
    public void setup() {
        MockitoAnnotations.initMocks(this);
        ExpressionResource expressionResource = new ExpressionResource();
        ReflectionTestUtils.setField(expressionResource, "expressionRepository", expressionRepository);
        this.restExpressionMockMvc = MockMvcBuilders.standaloneSetup(expressionResource).build();
    }

    @Before
    public void initTest() {
        expression = new Expression();
        expression.setExpression(DEFAULT_EXPRESSION);
        expression.setTranslation(DEFAULT_TRANSLATION);
        expression.setMasculine(DEFAULT_MASCULINE);
        expression.setFeminine(DEFAULT_FEMININE);
        expression.setSingular(DEFAULT_SINGULAR);
        expression.setPlural(DEFAULT_PLURAL);
        expression.setExample(DEFAULT_EXAMPLE);
        expression.setDefinition(DEFAULT_DEFINITION);
        expression.setOpposite(DEFAULT_OPPOSITE);
        expression.setComment(DEFAULT_COMMENT);
        expression.setPronunciation(DEFAULT_PRONUNCIATION);
        expression.setImage(DEFAULT_IMAGE);
        expression.setLatitude(DEFAULT_LATITUDE);
        expression.setLongitude(DEFAULT_LONGITUDE);
        expression.setPriority(DEFAULT_PRIORITY);
        expression.setMarked(DEFAULT_MARKED);
        expression.setCreatedDate(DEFAULT_CREATED_DATE);
        expression.setLastModifiedDate(DEFAULT_LAST_MODIFIED_DATE);
    }

    @Test
    @Transactional
    public void createExpression() throws Exception {
        // Validate the database is empty
        assertThat(expressionRepository.findAll()).hasSize(0);

        // Create the Expression
        restExpressionMockMvc.perform(post("/app/rest/expressions")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(expression)))
                .andExpect(status().isOk());

        // Validate the Expression in the database
        List<Expression> expressions = expressionRepository.findAll();
        assertThat(expressions).hasSize(1);
        Expression testExpression = expressions.iterator().next();
        assertThat(testExpression.getExpression()).isEqualTo(DEFAULT_EXPRESSION);
        assertThat(testExpression.getTranslation()).isEqualTo(DEFAULT_TRANSLATION);
        assertThat(testExpression.getMasculine()).isEqualTo(DEFAULT_MASCULINE);
        assertThat(testExpression.getFeminine()).isEqualTo(DEFAULT_FEMININE);
        assertThat(testExpression.getSingular()).isEqualTo(DEFAULT_SINGULAR);
        assertThat(testExpression.getPlural()).isEqualTo(DEFAULT_PLURAL);
        assertThat(testExpression.getExample()).isEqualTo(DEFAULT_EXAMPLE);
        assertThat(testExpression.getDefinition()).isEqualTo(DEFAULT_DEFINITION);
        assertThat(testExpression.getOpposite()).isEqualTo(DEFAULT_OPPOSITE);
        assertThat(testExpression.getComment()).isEqualTo(DEFAULT_COMMENT);
        assertThat(testExpression.getPronunciation()).isEqualTo(DEFAULT_PRONUNCIATION);
        assertThat(testExpression.getImage()).isEqualTo(DEFAULT_IMAGE);
        assertThat(testExpression.getLatitude()).isEqualTo(DEFAULT_LATITUDE);
        assertThat(testExpression.getLongitude()).isEqualTo(DEFAULT_LONGITUDE);
        assertThat(testExpression.getPriority()).isEqualTo(DEFAULT_PRIORITY);
        assertThat(testExpression.getMarked()).isEqualTo(DEFAULT_MARKED);
        assertThat(testExpression.getCreatedDate()).isEqualTo(DEFAULT_CREATED_DATE);
        assertThat(testExpression.getLastModifiedDate()).isEqualTo(DEFAULT_LAST_MODIFIED_DATE);
    }

    @Test
    @Transactional
    public void getAllExpressions() throws Exception {
        // Initialize the database
        expressionRepository.saveAndFlush(expression);

        // Get all the expressions
        restExpressionMockMvc.perform(get("/app/rest/expressions"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.totalElements").value(1))
            .andExpect(jsonPath("$.content.[0].id").value(expression.getId().intValue()))
            .andExpect(jsonPath("$.content.[0].expression").value(DEFAULT_EXPRESSION.toString()))
            .andExpect(jsonPath("$.content.[0].translation").value(DEFAULT_TRANSLATION.toString()))
            .andExpect(jsonPath("$.content.[0].masculine").value(DEFAULT_MASCULINE.booleanValue()))
            .andExpect(jsonPath("$.content.[0].feminine").value(DEFAULT_FEMININE.booleanValue()))
            .andExpect(jsonPath("$.content.[0].singular").value(DEFAULT_SINGULAR.booleanValue()))
            .andExpect(jsonPath("$.content.[0].plural").value(DEFAULT_PLURAL.booleanValue()))
            .andExpect(jsonPath("$.content.[0].example").value(DEFAULT_EXAMPLE.toString()))
            .andExpect(jsonPath("$.content.[0].definition").value(DEFAULT_DEFINITION.toString()))
            .andExpect(jsonPath("$.content.[0].opposite").value(DEFAULT_OPPOSITE.toString()))
            .andExpect(jsonPath("$.content.[0].comment").value(DEFAULT_COMMENT.toString()))
            .andExpect(jsonPath("$.content.[0].pronunciation").value(DEFAULT_PRONUNCIATION.toString()))
            .andExpect(jsonPath("$.content.[0].image").value(DEFAULT_IMAGE.booleanValue()))
            .andExpect(jsonPath("$.content.[0].latitude").value(DEFAULT_LATITUDE.intValue()))
            .andExpect(jsonPath("$.content.[0].longitude").value(DEFAULT_LONGITUDE.intValue()))
            .andExpect(jsonPath("$.content.[0].priority").value(DEFAULT_PRIORITY))
            .andExpect(jsonPath("$.content.[0].marked").value(DEFAULT_MARKED.booleanValue()))
            .andExpect(jsonPath("$.content.[0].createdDate").value(DEFAULT_CREATED_DATE_STR))
            .andExpect(jsonPath("$.content.[0].lastModifiedDate").value(DEFAULT_MODIFIED_STR));
    }

    @Test
    @Transactional
    public void getAllExpressionsPagination() throws Exception {
        // Initialize the database
        initDatabase();

        // Get all the expressions
        restExpressionMockMvc.perform(get("/app/rest/expressions?size=2&page=0"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.numberOfElements").value(2));
    }

    @Test
    @Transactional
    public void getAllExpressionsSorting() throws Exception {
        // Initialize the database
        initDatabase();

        // Get all the expressions
        restExpressionMockMvc.perform(get("/app/rest/expressions"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.totalElements").value(3))
            .andExpect(jsonPath("$.content.[0].expression").value("rouge"))
            .andExpect(jsonPath("$.content.[1].expression").value("aller"))
            .andExpect(jsonPath("$.content.[2].expression").value("maison"));
    }

    @Test
    @Transactional
    public void getAllExpressionsMarked() throws Exception {
        // Initialize the database
        initDatabase();

        // Get all the expressions
        restExpressionMockMvc.perform(get("/app/rest/expressions?marked=true"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.totalElements").value(1));
    }

    @Test
    @Transactional
    public void getAllExpressionsNotMarked() throws Exception {
        // Initialize the database
        initDatabase();

        // Get all the expressions
        restExpressionMockMvc.perform(get("/app/rest/expressions?marked=false"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.totalElements").value(2));
    }

    @Test
    @Transactional
    public void getAllExpressionsPriorities() throws Exception {
        // Initialize the database
        initDatabase();

        // Get all the expressions
        restExpressionMockMvc.perform(get("/app/rest/expressions?priorities=1,2"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.totalElements").value(2));
    }

    @Test
    @Transactional
    public void getAllExpressionsTags() throws Exception {
        // Initialize the database
        initDatabase();

        List<Tag> tags = tagRepository.findAll();
        Long tagVerbeId = null;
        Long tagAdjectifId = null;
        for (Tag tag : tags) {
            if (tag.getName().equals("verbe")) {
                tagVerbeId = tag.getId();
            }
            if (tag.getName().equals("adjectif")) {
                tagAdjectifId = tag.getId();
            }
        }

        // Get all the expressions
        String path = "/app/rest/expressions?tags=" + tagVerbeId + "," + tagAdjectifId;
        restExpressionMockMvc.perform(get(path))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.totalElements").value(2));
    }

    private void initDatabase() {
        Tag tagSubstantif = createTagSubstantif();
        tagRepository.saveAndFlush(tagSubstantif);
        Tag tagVerbe = createTagVerbe();
        tagRepository.saveAndFlush(tagVerbe);
        Tag tagAdjectif = createTagAdjectif();
        tagRepository.saveAndFlush(tagAdjectif);
        Tag tagCouleur = createTagCouleur();
        tagRepository.saveAndFlush(tagCouleur);

        expressionRepository.saveAndFlush(createExpressionMaison(tagSubstantif));
        expressionRepository.saveAndFlush(createExpressionAller(tagVerbe));
        expressionRepository.saveAndFlush(createExpressionRouge(tagAdjectif, tagCouleur));
    }

    private Expression createExpressionMaison(Tag tagSubstantif) {
        Expression result = new Expression();
        result.setExpression("maison");
        result.setTranslation("Haus");
        result.setMasculine(null);
        result.setFeminine(true);
        result.setSingular(false);
        result.setPlural(null);
        result.setExample("La Maison Blanche.");
        result.setDefinition(null);
        result.setOpposite(null);
        result.setComment(null);
        result.setPronunciation(null);
        result.setImage(false);
        result.setLatitude(null);
        result.setLongitude(null);
        result.setPriority(1);
        result.setMarked(true);
        result.setTags(new HashSet<>(Arrays.asList(tagSubstantif)));
        result.setCreatedDate(new DateTime());
        result.setLastModifiedDate(null);
        return result;
    }

    private Expression createExpressionAller(Tag tagVerbe) {
        Expression result = new Expression();
        result.setExpression("aller");
        result.setTranslation("gehen");
        result.setMasculine(null);
        result.setFeminine(null);
        result.setSingular(null);
        result.setPlural(null);
        result.setExample("Je vais à la maison.");
        result.setDefinition(null);
        result.setOpposite(null);
        result.setComment(null);
        result.setPronunciation(null);
        result.setImage(false);
        result.setLatitude(null);
        result.setLongitude(null);
        result.setPriority(2);
        result.setMarked(false);
        result.setTags(new HashSet<>(Arrays.asList(tagVerbe)));
        result.setCreatedDate(new DateTime());
        result.setLastModifiedDate(null);
        return result;
    }

    private Expression createExpressionRouge(Tag tagAdjectif, Tag tagCouleur) {
        Expression result = new Expression();
        result.setExpression("rouge");
        result.setTranslation("rot");
        result.setMasculine(true);
        result.setFeminine(true);
        result.setSingular(null);
        result.setPlural(null);
        result.setExample("La maison est rouge.");
        result.setDefinition(null);
        result.setOpposite(null);
        result.setComment(null);
        result.setPronunciation(null);
        result.setImage(false);
        result.setLatitude(null);
        result.setLongitude(null);
        result.setPriority(3);
        result.setMarked(false);
        result.setTags(new HashSet<>(Arrays.asList(tagAdjectif, tagCouleur)));
        result.setCreatedDate(new DateTime());
        result.setLastModifiedDate(null);
        return result;
    }

    private Tag createTagSubstantif() {
        Tag result = new Tag();
        result.setName("substantif");
        return result;
    }

    private Tag createTagVerbe() {
        Tag result = new Tag();
        result.setName("verbe");
        return result;
    }

    private Tag createTagAdjectif() {
        Tag result = new Tag();
        result.setName("adjectif");
        return result;
    }

    private Tag createTagCouleur() {
        Tag result = new Tag();
        result.setName("couleur");
        return result;
    }

    @Test
    @Transactional
    public void getExpression() throws Exception {
        // Initialize the database
        expressionRepository.saveAndFlush(expression);

        // Get the expression
        restExpressionMockMvc.perform(get("/app/rest/expressions/{id}", expression.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.id").value(expression.getId().intValue()))
            .andExpect(jsonPath("$.expression").value(DEFAULT_EXPRESSION.toString()))
            .andExpect(jsonPath("$.translation").value(DEFAULT_TRANSLATION.toString()))
            .andExpect(jsonPath("$.masculine").value(DEFAULT_MASCULINE.booleanValue()))
            .andExpect(jsonPath("$.feminine").value(DEFAULT_FEMININE.booleanValue()))
            .andExpect(jsonPath("$.singular").value(DEFAULT_SINGULAR.booleanValue()))
            .andExpect(jsonPath("$.plural").value(DEFAULT_PLURAL.booleanValue()))
            .andExpect(jsonPath("$.example").value(DEFAULT_EXAMPLE.toString()))
            .andExpect(jsonPath("$.definition").value(DEFAULT_DEFINITION.toString()))
            .andExpect(jsonPath("$.opposite").value(DEFAULT_OPPOSITE.toString()))
            .andExpect(jsonPath("$.comment").value(DEFAULT_COMMENT.toString()))
            .andExpect(jsonPath("$.pronunciation").value(DEFAULT_PRONUNCIATION.toString()))
            .andExpect(jsonPath("$.image").value(DEFAULT_IMAGE.booleanValue()))
            .andExpect(jsonPath("$.latitude").value(DEFAULT_LATITUDE.intValue()))
            .andExpect(jsonPath("$.longitude").value(DEFAULT_LONGITUDE.intValue()))
            .andExpect(jsonPath("$.priority").value(DEFAULT_PRIORITY))
            .andExpect(jsonPath("$.marked").value(DEFAULT_MARKED.booleanValue()))
            .andExpect(jsonPath("$.createdDate").value(DEFAULT_CREATED_DATE_STR))
            .andExpect(jsonPath("$.lastModifiedDate").value(DEFAULT_MODIFIED_STR));
    }

    @Test
    @Transactional
    public void getNonExistingExpression() throws Exception {
        // Get the expression
        restExpressionMockMvc.perform(get("/app/rest/expressions/{id}", 1L))
                .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateExpression() throws Exception {
        // Initialize the database
        expressionRepository.saveAndFlush(expression);

        // Update the expression
        expression.setExpression(UPDATED_EXPRESSION);
        expression.setTranslation(UPDATED_TRANSLATION);
        expression.setMasculine(UPDATED_MASCULINE);
        expression.setFeminine(UPDATED_FEMININE);
        expression.setSingular(UPDATED_SINGULAR);
        expression.setPlural(UPDATED_PLURAL);
        expression.setExample(UPDATED_EXAMPLE);
        expression.setDefinition(UPDATED_DEFINITION);
        expression.setOpposite(UPDATED_OPPOSITE);
        expression.setComment(UPDATED_COMMENT);
        expression.setPronunciation(UPDATED_PRONUNCIATION);
        expression.setImage(UPDATED_IMAGE);
        expression.setLatitude(UPDATED_LATITUDE);
        expression.setLongitude(UPDATED_LONGITUDE);
        expression.setPriority(UPDATED_PRIORITY);
        expression.setMarked(UPDATED_MARKED);
        expression.setCreatedDate(UPDATED_CREATED_DATE);
        expression.setLastModifiedDate(UPDATED_LAST_MODIFIED_DATE);
        restExpressionMockMvc.perform(post("/app/rest/expressions")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(expression)))
                .andExpect(status().isOk());

        // Validate the Expression in the database
        List<Expression> expressions = expressionRepository.findAll();
        assertThat(expressions).hasSize(1);
        Expression testExpression = expressions.iterator().next();
        assertThat(testExpression.getExpression()).isEqualTo(UPDATED_EXPRESSION);
        assertThat(testExpression.getTranslation()).isEqualTo(UPDATED_TRANSLATION);
        assertThat(testExpression.getMasculine()).isEqualTo(UPDATED_MASCULINE);
        assertThat(testExpression.getFeminine()).isEqualTo(UPDATED_FEMININE);
        assertThat(testExpression.getSingular()).isEqualTo(UPDATED_SINGULAR);
        assertThat(testExpression.getPlural()).isEqualTo(UPDATED_PLURAL);
        assertThat(testExpression.getExample()).isEqualTo(UPDATED_EXAMPLE);
        assertThat(testExpression.getDefinition()).isEqualTo(UPDATED_DEFINITION);
        assertThat(testExpression.getOpposite()).isEqualTo(UPDATED_OPPOSITE);
        assertThat(testExpression.getComment()).isEqualTo(UPDATED_COMMENT);
        assertThat(testExpression.getPronunciation()).isEqualTo(UPDATED_PRONUNCIATION);
        assertThat(testExpression.getImage()).isEqualTo(UPDATED_IMAGE);
        assertThat(testExpression.getLatitude()).isEqualTo(UPDATED_LATITUDE);
        assertThat(testExpression.getLongitude()).isEqualTo(UPDATED_LONGITUDE);
        assertThat(testExpression.getPriority()).isEqualTo(UPDATED_PRIORITY);
        assertThat(testExpression.getMarked()).isEqualTo(UPDATED_MARKED);
        assertThat(testExpression.getCreatedDate()).isEqualTo(UPDATED_CREATED_DATE);
        assertThat(testExpression.getLastModifiedDate()).isEqualTo(UPDATED_LAST_MODIFIED_DATE);
    }

    @Test
    @Transactional
    public void deleteExpression() throws Exception {
        // Initialize the database
        expressionRepository.saveAndFlush(expression);

        // Get the expression
        restExpressionMockMvc.perform(delete("/app/rest/expressions/{id}", expression.getId())
                .accept(TestUtil.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk());

        // Validate the database is empty
        List<Expression> expressions = expressionRepository.findAll();
        assertThat(expressions).hasSize(0);
    }
}
