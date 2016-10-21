package com.oranje.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.io.Serializable;
import java.util.Objects;

/**
 * A Player.
 */

@Document(collection = "player")
public class Player implements Serializable {

    @Id
    private String id;

    @Field("name")
    private String name;
    
    @Field("three_points_made")
    private Double threePointsMade;
    
    @Field("three_points_pct")
    private Double threePointsPct;
    
    @Field("assists")
    private Double assists;
    
    @Field("blocks")
    private Double blocks;
    
    @Field("free_throw_made")
    private Double freeThrowMade;
    
    @Field("free_throw_pct")
    private Double freeThrowPct;
    
    @Field("field_goals_pct")
    private Double fieldGoalsPct;
    
    @Field("games_played")
    private Integer gamesPlayed;
    
    @Field("minutes")
    private Double minutes;
    
    @Field("points")
    private Double points;
    
    @Field("rebounds")
    private Double rebounds;
    
    @Field("steals")
    private Double steals;
    
    @Field("turnovers")
    private Double turnovers;
    
    @Field("value")
    private Double value;
    
    @Field("has_both")
    private Boolean hasBoth;
    
    private String team;
    
    
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }

    public Double getThreePointsMade() {
        return threePointsMade;
    }
    
    public void setThreePointsMade(Double threePointsMade) {
        this.threePointsMade = threePointsMade;
    }

    public Double getThreePointsPct() {
        return threePointsPct;
    }
    
    public void setThreePointsPct(Double threePointsPct) {
        this.threePointsPct = threePointsPct;
    }

    public Double getAssists() {
        return assists;
    }
    
    public void setAssists(Double assists) {
        this.assists = assists;
    }

    public Double getBlocks() {
        return blocks;
    }
    
    public void setBlocks(Double blocks) {
        this.blocks = blocks;
    }

    public Double getFreeThrowMade() {
        return freeThrowMade;
    }
    
    public void setFreeThrowMade(Double freeThrowMade) {
        this.freeThrowMade = freeThrowMade;
    }

    public Double getFreeThrowPct() {
        return freeThrowPct;
    }
    
    public void setFreeThrowPct(Double freeThrowPct) {
        this.freeThrowPct = freeThrowPct;
    }

    public Double getFieldGoalsPct() {
        return fieldGoalsPct;
    }
    
    public void setFieldGoalsPct(Double fieldGoalsPct) {
        this.fieldGoalsPct = fieldGoalsPct;
    }

    public Integer getGamesPlayed() {
        return gamesPlayed;
    }
    
    public void setGamesPlayed(Integer gamesPlayed) {
        this.gamesPlayed = gamesPlayed;
    }

    public Double getMinutes() {
        return minutes;
    }
    
    public void setMinutes(Double minutes) {
        this.minutes = minutes;
    }

    public Double getPoints() {
        return points;
    }
    
    public void setPoints(Double points) {
        this.points = points;
    }

    public Double getRebounds() {
        return rebounds;
    }
    
    public void setRebounds(Double rebounds) {
        this.rebounds = rebounds;
    }

    public Double getSteals() {
        return steals;
    }
    
    public void setSteals(Double steals) {
        this.steals = steals;
    }

    public Double getTurnovers() {
        return turnovers;
    }
    
    public void setTurnovers(Double turnovers) {
        this.turnovers = turnovers;
    }

    public Double getValue() {
        return value;
    }
    
    public void setValue(Double value) {
        this.value = value;
    }

    public Boolean getHasBoth() {
        return hasBoth;
    }
    
    public void setHasBoth(Boolean hasBoth) {
        this.hasBoth = hasBoth;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Player player = (Player) o;
        if(player.id == null || id == null) {
            return false;
        }
        return Objects.equals(id, player.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "Player{" +
            "id=" + id +
            ", name='" + name + "'" +
            ", threePointsMade='" + threePointsMade + "'" +
            ", threePointsPct='" + threePointsPct + "'" +
            ", assists='" + assists + "'" +
            ", blocks='" + blocks + "'" +
            ", freeThrowMade='" + freeThrowMade + "'" +
            ", freeThrowPct='" + freeThrowPct + "'" +
            ", fieldGoalsPct='" + fieldGoalsPct + "'" +
            ", gamesPlayed='" + gamesPlayed + "'" +
            ", minutes='" + minutes + "'" +
            ", points='" + points + "'" +
            ", rebounds='" + rebounds + "'" +
            ", steals='" + steals + "'" +
            ", turnovers='" + turnovers + "'" +
            ", value='" + value + "'" +
            ", hasBoth='" + hasBoth + "'" +
            '}';
    }

	public String getTeam() {
		return team;
	}

	public void setTeam(String team) {
		this.team = team;
	}
}
