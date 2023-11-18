package com.felipesantos.warmine.entities;

import java.util.Objects;

public class Player {
    private String name;
    private WarTeam warTeam;

    public Player(String name, WarTeam warTeam) {
        this.name = name;
        this.warTeam = warTeam;
    }

    public Player(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public WarTeam getWarTeam() {
        return warTeam;
    }

    public void setTeam(WarTeam warTeam) {
        this.warTeam = warTeam;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Player player = (Player) o;
        return Objects.equals(name, player.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
