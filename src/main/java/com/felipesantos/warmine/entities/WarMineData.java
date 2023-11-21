package com.felipesantos.warmine.entities;

import com.felipesantos.warmine.events.CrownBreakEvent;
import net.minecraft.util.math.BlockPos;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class WarMineData {

    public static final int INITIAL_SCORE = 0;
    public static final int CROWN_RANGE = 64;
    public static final int CITY_RANGE = 32;
    public static boolean DAY_OF_WAR = false;
    private List<WarTeam> warTeams;
    private List<Player> players;
    private List<CrownDataBlock> capitals;
    private List<CityDataBlock> cities;

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

    public List<CityDataBlock> getCities() {
        return cities;
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

    public void setCities(List<CityDataBlock> cities) {
        this.cities = cities;
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

    public static CrownDataBlock getCapital(WarTeam warTeam){
        List<CrownDataBlock> capitalsResult = MinecraftData.warmine.getCapitals().stream()
                .filter((capitals) -> capitals.getWarTeam().equals(warTeam)).collect(Collectors.toList());

        if(capitalsResult.size() != 1){
            return null;
        } else {
            return capitalsResult.get(0);
        }
    }

    public static CrownDataBlock getCapital(BlockPos pos){
        return getCapital(new Coordinate(pos.getX(), pos.getY(), pos.getZ()));
    }

    public static CityDataBlock getCity(String cityName){
        List<CityDataBlock> cityResult = MinecraftData.warmine.getCities().stream()
                .filter((city) -> city.getName().equalsIgnoreCase(cityName)).collect(Collectors.toList());
        if(cityResult.size() != 1){
            return null;
        } else {
            return cityResult.get(0);
        }
    }

    public static CityDataBlock getCity(Coordinate coordinate){
        List<CityDataBlock> cityResult = MinecraftData.warmine.getCities().stream()
                .filter((cities)->cities.getCoordinate().isSamePosition(coordinate)).collect(Collectors.toList());
        if(cityResult.size() != 1){
            return null;
        } else {
            return cityResult.get(0);
        }
    }

    public static CityDataBlock getCity(BlockPos pos){
        return getCity(new Coordinate(pos.getX(),pos.getY(),pos.getZ()));
    }

    public static AbstractCityBlock getTerritoryBlock(BlockPos pos){
        AbstractCityBlock abstractTerritoryBlock = getCapital(pos);
        if(abstractTerritoryBlock == null){
            abstractTerritoryBlock = getCity(pos);
        }
        return abstractTerritoryBlock;
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

    public static CrownDataBlock getCapitalInArea(BlockPos pos){
        return getCapitalInArea(new Coordinate(pos.getX(),pos.getY(), pos.getZ()));
    }

    public static CityDataBlock getCityInArea(Coordinate coordinate){
        List<CityDataBlock> citiesResult = MinecraftData.warmine.getCities().stream()
                .filter((cities)->cities.getCoordinate().inArea(cities.getRANGE_AREA(), coordinate)).collect(Collectors.toList());

        if(citiesResult.size() != 1){
            if(citiesResult.size() == 0){
                return null;
            } else {
                throw new IllegalStateException("Two or more Cities Block in same Area");
            }

        } else{
            return citiesResult.get(0);
        }
    }

    public static CityDataBlock getCityInArea(BlockPos pos){
        return getCityInArea(new Coordinate(pos.getX(),pos.getY(), pos.getZ()));
    }

    public static boolean isAboutArea(BlockPos pos,int range){
        boolean isAbout = false;
        if(WarMineData.getCapitalInArea(new Coordinate(pos.getX()+range,pos.getZ()-range)) != null){
            isAbout = true;
        }
        if(WarMineData.getCapitalInArea(new Coordinate(pos.getX()-range,pos.getZ()+range)) != null){
            isAbout = true;
        }
        if(WarMineData.getCapitalInArea(new Coordinate(pos.getX()+range,pos.getZ()+range)) != null){
            isAbout = true;
        }
        if(WarMineData.getCapitalInArea(new Coordinate(pos.getX()-range,pos.getZ()-range)) != null){
            isAbout = true;
        }

        if(WarMineData.getCityInArea(new Coordinate(pos.getX()+range,pos.getZ()-range)) != null){
            isAbout = true;
        }
        if(WarMineData.getCityInArea(new Coordinate(pos.getX()-range,pos.getZ()+range)) != null){
            isAbout = true;
        }
        if(WarMineData.getCityInArea(new Coordinate(pos.getX()+range,pos.getZ()+range)) != null){
            isAbout = true;
        }
        if(WarMineData.getCityInArea(new Coordinate(pos.getX()-range,pos.getZ()-range)) != null){
            isAbout = true;
        }

        return isAbout;
    }

    public static AbstractCityBlock getTerritoryBlockInArea(BlockPos pos){
        AbstractCityBlock territoryBlock = getCapitalInArea(pos);
        if(territoryBlock == null){
            territoryBlock = getCityInArea(pos);
        }
        return territoryBlock;
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
                    removeTeamOfCity(warTeam);
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
        CrownDataBlock crownForDelete= null;
        if(!capitals.isEmpty()){
            for(CrownDataBlock crownBlock : capitals){
                if(crownBlock.getWarTeam() != null){
                    if(crownBlock.getWarTeam().getName().equalsIgnoreCase(warTeam.getName())){
                        CrownBreakEvent.deleteNameOfBlock(MinecraftData.world.getServer().func_241755_D_(),crownBlock);
                        MinecraftData.world.destroyBlock(crownBlock.getCoordinate().toBlockPos(), false);
                        crownForDelete = crownBlock;
                    }
                }
            }
            capitals.remove(crownForDelete);
        }
    }

    public void removeTeamOfCity(WarTeam warTeam){
        CityDataBlock cityForRemove = null;
        if(!cities.isEmpty()){
            for(CityDataBlock cityBlock : cities){
                if(cityBlock.getWarTeam() != null){
                    if(cityBlock.getWarTeam().getName().equalsIgnoreCase(warTeam.getName())){
                        CrownBreakEvent.deleteNameOfBlock(MinecraftData.world.getServer().func_241755_D_(),cityBlock);
                        MinecraftData.world.destroyBlock(cityBlock.getCoordinate().toBlockPos(), false);
                        cityForRemove = cityBlock;
                    }
                }
            }
        }
        cities.remove(cityForRemove);
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
