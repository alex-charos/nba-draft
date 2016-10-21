package com.oranje.web.rest;

import com.oranje.Application;
import com.oranje.domain.Player;
import com.oranje.repository.PlayerRepository;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import static org.hamcrest.Matchers.hasItem;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


/**
 * Test class for the PlayerResource REST controller.
 *
 * @see PlayerResource
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
@IntegrationTest
public class PlayerResourceIntTest {

    private static final String DEFAULT_NAME = "AAAAA";
    private static final String UPDATED_NAME = "BBBBB";

    private static final Double DEFAULT_THREE_POINTS_MADE = 1D;
    private static final Double UPDATED_THREE_POINTS_MADE = 2D;

    private static final Double DEFAULT_THREE_POINTS_PCT = 1D;
    private static final Double UPDATED_THREE_POINTS_PCT = 2D;

    private static final Double DEFAULT_ASSISTS = 1D;
    private static final Double UPDATED_ASSISTS = 2D;

    private static final Double DEFAULT_BLOCKS = 1D;
    private static final Double UPDATED_BLOCKS = 2D;

    private static final Double DEFAULT_FREE_THROW_MADE = 1D;
    private static final Double UPDATED_FREE_THROW_MADE = 2D;

    private static final Double DEFAULT_FREE_THROW_PCT = 1D;
    private static final Double UPDATED_FREE_THROW_PCT = 2D;

    private static final Double DEFAULT_FIELD_GOALS_PCT = 1D;
    private static final Double UPDATED_FIELD_GOALS_PCT = 2D;

    private static final Integer DEFAULT_GAMES_PLAYED = 1;
    private static final Integer UPDATED_GAMES_PLAYED = 2;

    private static final Double DEFAULT_MINUTES = 1D;
    private static final Double UPDATED_MINUTES = 2D;

    private static final Double DEFAULT_POINTS = 1D;
    private static final Double UPDATED_POINTS = 2D;

    private static final Double DEFAULT_REBOUNDS = 1D;
    private static final Double UPDATED_REBOUNDS = 2D;

    private static final Double DEFAULT_STEALS = 1D;
    private static final Double UPDATED_STEALS = 2D;

    private static final Double DEFAULT_TURNOVERS = 1D;
    private static final Double UPDATED_TURNOVERS = 2D;

    private static final Double DEFAULT_VALUE = 1D;
    private static final Double UPDATED_VALUE = 2D;

    private static final Boolean DEFAULT_HAS_BOTH = false;
    private static final Boolean UPDATED_HAS_BOTH = true;

    @Inject
    private PlayerRepository playerRepository;

    @Inject
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Inject
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    private MockMvc restPlayerMockMvc;

    private Player player;

    @PostConstruct
    public void setup() {
        MockitoAnnotations.initMocks(this);
        PlayerResource playerResource = new PlayerResource();
        ReflectionTestUtils.setField(playerResource, "playerRepository", playerRepository);
        this.restPlayerMockMvc = MockMvcBuilders.standaloneSetup(playerResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setMessageConverters(jacksonMessageConverter).build();
    }

    @Before
    public void initTest() {
        playerRepository.deleteAll();
        player = new Player();
        player.setName(DEFAULT_NAME);
        player.setThreePointsMade(DEFAULT_THREE_POINTS_MADE);
        player.setThreePointsPct(DEFAULT_THREE_POINTS_PCT);
        player.setAssists(DEFAULT_ASSISTS);
        player.setBlocks(DEFAULT_BLOCKS);
        player.setFreeThrowMade(DEFAULT_FREE_THROW_MADE);
        player.setFreeThrowPct(DEFAULT_FREE_THROW_PCT);
        player.setFieldGoalsPct(DEFAULT_FIELD_GOALS_PCT);
        player.setGamesPlayed(DEFAULT_GAMES_PLAYED);
        player.setMinutes(DEFAULT_MINUTES);
        player.setPoints(DEFAULT_POINTS);
        player.setRebounds(DEFAULT_REBOUNDS);
        player.setSteals(DEFAULT_STEALS);
        player.setTurnovers(DEFAULT_TURNOVERS);
        player.setValue(DEFAULT_VALUE);
        player.setHasBoth(DEFAULT_HAS_BOTH);
    }

    @Test
    public void createPlayer() throws Exception {
        int databaseSizeBeforeCreate = playerRepository.findAll().size();

        // Create the Player

        restPlayerMockMvc.perform(post("/api/players")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(player)))
                .andExpect(status().isCreated());

        // Validate the Player in the database
        List<Player> players = playerRepository.findAll();
        assertThat(players).hasSize(databaseSizeBeforeCreate + 1);
        Player testPlayer = players.get(players.size() - 1);
        assertThat(testPlayer.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testPlayer.getThreePointsMade()).isEqualTo(DEFAULT_THREE_POINTS_MADE);
        assertThat(testPlayer.getThreePointsPct()).isEqualTo(DEFAULT_THREE_POINTS_PCT);
        assertThat(testPlayer.getAssists()).isEqualTo(DEFAULT_ASSISTS);
        assertThat(testPlayer.getBlocks()).isEqualTo(DEFAULT_BLOCKS);
        assertThat(testPlayer.getFreeThrowMade()).isEqualTo(DEFAULT_FREE_THROW_MADE);
        assertThat(testPlayer.getFreeThrowPct()).isEqualTo(DEFAULT_FREE_THROW_PCT);
        assertThat(testPlayer.getFieldGoalsPct()).isEqualTo(DEFAULT_FIELD_GOALS_PCT);
        assertThat(testPlayer.getGamesPlayed()).isEqualTo(DEFAULT_GAMES_PLAYED);
        assertThat(testPlayer.getMinutes()).isEqualTo(DEFAULT_MINUTES);
        assertThat(testPlayer.getPoints()).isEqualTo(DEFAULT_POINTS);
        assertThat(testPlayer.getRebounds()).isEqualTo(DEFAULT_REBOUNDS);
        assertThat(testPlayer.getSteals()).isEqualTo(DEFAULT_STEALS);
        assertThat(testPlayer.getTurnovers()).isEqualTo(DEFAULT_TURNOVERS);
        assertThat(testPlayer.getValue()).isEqualTo(DEFAULT_VALUE);
        assertThat(testPlayer.getHasBoth()).isEqualTo(DEFAULT_HAS_BOTH);
    }

    @Test
    public void getAllPlayers() throws Exception {
        // Initialize the database
        playerRepository.save(player);

        // Get all the players
        restPlayerMockMvc.perform(get("/api/players?sort=id,desc"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.[*].id").value(hasItem(player.getId())))
                .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME.toString())))
                .andExpect(jsonPath("$.[*].threePointsMade").value(hasItem(DEFAULT_THREE_POINTS_MADE.doubleValue())))
                .andExpect(jsonPath("$.[*].threePointsPct").value(hasItem(DEFAULT_THREE_POINTS_PCT.doubleValue())))
                .andExpect(jsonPath("$.[*].assists").value(hasItem(DEFAULT_ASSISTS.doubleValue())))
                .andExpect(jsonPath("$.[*].blocks").value(hasItem(DEFAULT_BLOCKS.doubleValue())))
                .andExpect(jsonPath("$.[*].freeThrowMade").value(hasItem(DEFAULT_FREE_THROW_MADE.doubleValue())))
                .andExpect(jsonPath("$.[*].freeThrowPct").value(hasItem(DEFAULT_FREE_THROW_PCT.doubleValue())))
                .andExpect(jsonPath("$.[*].fieldGoalsPct").value(hasItem(DEFAULT_FIELD_GOALS_PCT.doubleValue())))
                .andExpect(jsonPath("$.[*].gamesPlayed").value(hasItem(DEFAULT_GAMES_PLAYED)))
                .andExpect(jsonPath("$.[*].minutes").value(hasItem(DEFAULT_MINUTES.doubleValue())))
                .andExpect(jsonPath("$.[*].points").value(hasItem(DEFAULT_POINTS.doubleValue())))
                .andExpect(jsonPath("$.[*].rebounds").value(hasItem(DEFAULT_REBOUNDS.doubleValue())))
                .andExpect(jsonPath("$.[*].steals").value(hasItem(DEFAULT_STEALS.doubleValue())))
                .andExpect(jsonPath("$.[*].turnovers").value(hasItem(DEFAULT_TURNOVERS.doubleValue())))
                .andExpect(jsonPath("$.[*].value").value(hasItem(DEFAULT_VALUE.doubleValue())))
                .andExpect(jsonPath("$.[*].hasBoth").value(hasItem(DEFAULT_HAS_BOTH.booleanValue())));
    }

    @Test
    public void getPlayer() throws Exception {
        // Initialize the database
        playerRepository.save(player);

        // Get the player
        restPlayerMockMvc.perform(get("/api/players/{id}", player.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.id").value(player.getId()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME.toString()))
            .andExpect(jsonPath("$.threePointsMade").value(DEFAULT_THREE_POINTS_MADE.doubleValue()))
            .andExpect(jsonPath("$.threePointsPct").value(DEFAULT_THREE_POINTS_PCT.doubleValue()))
            .andExpect(jsonPath("$.assists").value(DEFAULT_ASSISTS.doubleValue()))
            .andExpect(jsonPath("$.blocks").value(DEFAULT_BLOCKS.doubleValue()))
            .andExpect(jsonPath("$.freeThrowMade").value(DEFAULT_FREE_THROW_MADE.doubleValue()))
            .andExpect(jsonPath("$.freeThrowPct").value(DEFAULT_FREE_THROW_PCT.doubleValue()))
            .andExpect(jsonPath("$.fieldGoalsPct").value(DEFAULT_FIELD_GOALS_PCT.doubleValue()))
            .andExpect(jsonPath("$.gamesPlayed").value(DEFAULT_GAMES_PLAYED))
            .andExpect(jsonPath("$.minutes").value(DEFAULT_MINUTES.doubleValue()))
            .andExpect(jsonPath("$.points").value(DEFAULT_POINTS.doubleValue()))
            .andExpect(jsonPath("$.rebounds").value(DEFAULT_REBOUNDS.doubleValue()))
            .andExpect(jsonPath("$.steals").value(DEFAULT_STEALS.doubleValue()))
            .andExpect(jsonPath("$.turnovers").value(DEFAULT_TURNOVERS.doubleValue()))
            .andExpect(jsonPath("$.value").value(DEFAULT_VALUE.doubleValue()))
            .andExpect(jsonPath("$.hasBoth").value(DEFAULT_HAS_BOTH.booleanValue()));
    }

    @Test
    public void getNonExistingPlayer() throws Exception {
        // Get the player
        restPlayerMockMvc.perform(get("/api/players/{id}", Long.MAX_VALUE))
                .andExpect(status().isNotFound());
    }

    @Test
    public void updatePlayer() throws Exception {
        // Initialize the database
        playerRepository.save(player);

		int databaseSizeBeforeUpdate = playerRepository.findAll().size();

        // Update the player
        player.setName(UPDATED_NAME);
        player.setThreePointsMade(UPDATED_THREE_POINTS_MADE);
        player.setThreePointsPct(UPDATED_THREE_POINTS_PCT);
        player.setAssists(UPDATED_ASSISTS);
        player.setBlocks(UPDATED_BLOCKS);
        player.setFreeThrowMade(UPDATED_FREE_THROW_MADE);
        player.setFreeThrowPct(UPDATED_FREE_THROW_PCT);
        player.setFieldGoalsPct(UPDATED_FIELD_GOALS_PCT);
        player.setGamesPlayed(UPDATED_GAMES_PLAYED);
        player.setMinutes(UPDATED_MINUTES);
        player.setPoints(UPDATED_POINTS);
        player.setRebounds(UPDATED_REBOUNDS);
        player.setSteals(UPDATED_STEALS);
        player.setTurnovers(UPDATED_TURNOVERS);
        player.setValue(UPDATED_VALUE);
        player.setHasBoth(UPDATED_HAS_BOTH);

        restPlayerMockMvc.perform(put("/api/players")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(player)))
                .andExpect(status().isOk());

        // Validate the Player in the database
        List<Player> players = playerRepository.findAll();
        assertThat(players).hasSize(databaseSizeBeforeUpdate);
        Player testPlayer = players.get(players.size() - 1);
        assertThat(testPlayer.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testPlayer.getThreePointsMade()).isEqualTo(UPDATED_THREE_POINTS_MADE);
        assertThat(testPlayer.getThreePointsPct()).isEqualTo(UPDATED_THREE_POINTS_PCT);
        assertThat(testPlayer.getAssists()).isEqualTo(UPDATED_ASSISTS);
        assertThat(testPlayer.getBlocks()).isEqualTo(UPDATED_BLOCKS);
        assertThat(testPlayer.getFreeThrowMade()).isEqualTo(UPDATED_FREE_THROW_MADE);
        assertThat(testPlayer.getFreeThrowPct()).isEqualTo(UPDATED_FREE_THROW_PCT);
        assertThat(testPlayer.getFieldGoalsPct()).isEqualTo(UPDATED_FIELD_GOALS_PCT);
        assertThat(testPlayer.getGamesPlayed()).isEqualTo(UPDATED_GAMES_PLAYED);
        assertThat(testPlayer.getMinutes()).isEqualTo(UPDATED_MINUTES);
        assertThat(testPlayer.getPoints()).isEqualTo(UPDATED_POINTS);
        assertThat(testPlayer.getRebounds()).isEqualTo(UPDATED_REBOUNDS);
        assertThat(testPlayer.getSteals()).isEqualTo(UPDATED_STEALS);
        assertThat(testPlayer.getTurnovers()).isEqualTo(UPDATED_TURNOVERS);
        assertThat(testPlayer.getValue()).isEqualTo(UPDATED_VALUE);
        assertThat(testPlayer.getHasBoth()).isEqualTo(UPDATED_HAS_BOTH);
    }

    @Test
    public void deletePlayer() throws Exception {
        // Initialize the database
        playerRepository.save(player);

		int databaseSizeBeforeDelete = playerRepository.findAll().size();

        // Get the player
        restPlayerMockMvc.perform(delete("/api/players/{id}", player.getId())
                .accept(TestUtil.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk());

        // Validate the database is empty
        List<Player> players = playerRepository.findAll();
        assertThat(players).hasSize(databaseSizeBeforeDelete - 1);
    }
}
