package com.felipesantos.warmine.entities;

import net.minecraft.util.math.BlockPos;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class WarMineData {

    public static final int INITIAL_SCORE = 0;
    public static final int CROWN_RANGE = 64;
    private List<WarTeam> warTeams;
    private List<Player> players;
    private List<CrownDataBlock> capitals;

    public WarMineData(List<WarTeam> warTeams, List<Player> players, List<CrownDataBlock> capitals) {
        this.warTeams = warTeams;
        this.players = players;
        this.capitals = capitals;
    }

    public WarMineData() {
    }

    public List<WarTeam> getTeams() {
        return warTeams;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public List<CrownDataBlock> getCapitals() {
        return capitals;
    }

    public void setTeams(List<WarTeam> warTeams) {
        this.warTeams = warTeams;
    }

    public void setPlayers(List<Player> players) {
        this.players = players;
    }

    public void setCapitals(List<CrownDataBlock> capitals) {
        this.capitals = capitals;
    }

    public static WarTeam getWarTeam(String teamPlayer){
        List<WarTeam> teamsResult = MinecraftData.warmine.getTeams().stream()
                .filter((team)-> team.getName().equals(teamPlayer)).collect(Collectors.toList());
        if(teamsResult.size() != 1){
            return null;
        } else {
            return teamsResult.get(0);
        }
    }

    public static Player getPlayer(String namePlayer){
        List<Player> playersResult = MinecraftData.warmine.getPlayers().stream()
                .filter((player)-> player.getName().equals(namePlayer)).collect(Collectors.toList());
        if(playersResult.size() != 1){
            return null;
        } else {
            return playersResult.get(0);
        }
    }

    public static CrownDataBlock getCapital(Coordinate coordinate){
        List<CrownDataBlock> capitalsResult = MinecraftData.warmine.getCapitals().stream()
                .filter((capitals)-> capitals.getCoordinate().isSamePosition(coordinate)).collect(Collectors.toList());
        if(capitalsResult.size() != 1){
            return null;
        } else {
            return capitalsResult.get(0);
        }
    }

    public static CrownDataBlock getCapital(BlockPos pos){
        return getCapital(new Coordinate(pos.getX(), pos.getY(), pos.getZ()));
    }

    public static CrownDataBlock getCapitalInArea(Coordinate coordinate){

        List<CrownDataBlock> capitalsResult = MinecraftData.warmine.getCapitals().stream()
                .filter((capital)-> capital.getCoordinate().inArea(capital.getRANGE_AREA(), coordinate)).collect(Collectors.toList());

        if(capitalsResult.size() != 1){
            if(capitalsResult.size() == 0){
                return null;
            } else {
                throw new IllegalStateException("Two or more Crown Block in same Area");
            }

        } else{
            return capitalsResult.get(0);
        }
    }

    public static boolean isAboutArea(BlockPos pos){
        boolean isAbout = false;
        if(WarMineData.getCapitalInArea(new Coordinate(pos.getX()+WarMineData.CROWN_RANGE,pos.getZ()-WarMineData.CROWN_RANGE)) != null){
            isAbout = true;
        }
        if(WarMineData.getCapitalInArea(new Coordinate(pos.getX()-WarMineData.CROWN_RANGE,pos.getZ()+WarMineData.CROWN_RANGE)) != null){
            isAbout = true;
        }
        if(WarMineData.getCapitalInArea(new Coordinate(pos.getX()+WarMineData.CROWN_RANGE,pos.getZ()+WarMineData.CROWN_RANGE)) != null){
            isAbout = true;
        }
        if(WarMineData.getCapitalInArea(new Coordinate(pos.getX()-WarMineData.CROWN_RANGE,pos.getZ()-WarMineData.CROWN_RANGE)) != null){
            isAbout = true;
        }
        return isAbout;
    }

    public static CrownDataBlock getCapitalInArea(BlockPos pos){
        return getCapitalInArea(new Coordinate(pos.getX(),pos.getY(), pos.getZ()));
    }

    public static boolean teamAlreadyHaveACapital(WarTeam warTeam){
        boolean exist = false;
        for(CrownDataBlock crown : MinecraftData.warmine.getCapitals()){
            if(crown.getWarTeam().equals(warTeam)){
                exist = true;
            }
        }
        return exist;
    }

    public boolean removeTeam(String name){
        boolean teamRemoved = false;
        List<WarTeam> teamsForDelete = new ArrayList<>();
            for(WarTeam warTeam : warTeams){
                if(warTeam.getName().equalsIgnoreCase(name)){
                    removeTeamOfPlayers(warTeam);
                    removeTeamOfCapitals(warTeam);
                    removeTeamofWarTeams(warTeam);
                    teamsForDelete.add(warTeam);
                    teamRemoved = true;
                }
            }
            warTeams.removeAll(teamsForDelete);
        return teamRemoved;
    }

    public void removeTeamOfPlayers(WarTeam warTeam){
        if(!players.isEmpty()){
            for(Player player : players){
                if(player.getWarTeam() != null){
                    if(player.getWarTeam().getName().equalsIgnoreCase(warTeam.getName())){
                        player.setTeam(null);
                    }
                }
            }
        }
    }

    public void removeTeamOfCapitals(WarTeam warTeam){
        if(!capitals.isEmpty()){
            for(CrownDataBlock crownBlock : capitals){
                if(crownBlock.getWarTeam() != null){
                    if(crownBlock.getWarTeam().getName().equalsIgnoreCase(warTeam.getName())){
                        crownBlock.setWarTeam(null);
                    }
                }
            }
        }
    }

    public void removeTeamofWarTeams(WarTeam warTeam){
        if(!warTeams.isEmpty()){
            for(WarTeam warDataTeam : warTeams){
                warDataTeam.getTeamsInWar().remove(warTeam.getName());
            }
        }
    }

    public Player playerDataExist(String namePlayer){
        Player playerExist = null;
        for(Player player : players){
            if(player.getName().equalsIgnoreCase(namePlayer)){
                playerExist = player;
            }
        }
        return playerExist;
    }
}
