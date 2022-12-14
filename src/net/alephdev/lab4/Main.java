/*
 * Created by MixaDev
 * https://vk.com/mixadev
 */
package net.alephdev.lab4;

import net.alephdev.lab4.alive.City;
import net.alephdev.lab4.alive.Community;
import net.alephdev.lab4.alive.Human;
import net.alephdev.lab4.alive.groups.Citizens;
import net.alephdev.lab4.alive.groups.PoorStratum;
import net.alephdev.lab4.alive.groups.RichStratum;
import net.alephdev.lab4.exceptions.RocketExplosionException;
import net.alephdev.lab4.inanimate.Planet;
import net.alephdev.lab4.inanimate.SpaceObject;
import net.alephdev.lab4.inanimate.rocket.Rocket;
import net.alephdev.lab4.inanimate.rocket.RocketModule;
import net.alephdev.lab4.inanimate.rocket.RocketRoom;
import net.alephdev.lab4.stuff.AbsoluteLocation;
import net.alephdev.lab4.stuff.AstronomicalInstruments;
import net.alephdev.lab4.stuff.Feeling;
import net.alephdev.lab4.stuff.GravitonLocator;
import net.alephdev.lab4.stuff.GravitonTelescope;
import net.alephdev.lab4.stuff.Location;
import net.alephdev.lab4.stuff.Material;
import net.alephdev.lab4.stuff.Origin;
import net.alephdev.lab4.stuff.Seed;
import net.alephdev.lab4.stuff.shapes.Cone;
import net.alephdev.lab4.stuff.shapes.Cylinder;

/**
 *
 * @author MixaDev
 */
public class Main {
    public static void main(String[] args) {
        Material steel = new Material("сталь");
        Cylinder basicRocketCylinder = new Cylinder(10, 10);
        Cone rocketLivingModule = new Cone(10, 0, 10);
        
        Planet earth = new Planet("Земля", new AbsoluteLocation(100, 100, 100), 9.8, 5.972E24);
        Planet moon = new Planet("Луна", new AbsoluteLocation(100, 200, 200), 1.6, 7.36E22);
        Location moonLocation = new Location(moon);
        Location spaceCityLocation = new Location(earth);
        City spaceCity = new City("Космический городок", spaceCityLocation, 500);
        Rocket.Parts rocketParts = new Rocket.Parts("детали ракеты");
        Rocket rocket = new Rocket("ракета", rocketParts, 100000, spaceCity);
        rocket.addModule(new RocketModule(1, steel, basicRocketCylinder));
        rocket.addModule(new RocketModule(2, steel, basicRocketCylinder));
        rocket.addModule(new RocketModule(3, steel, basicRocketCylinder));
        RocketModule livingModule = new RocketModule(4, steel, rocketLivingModule);
        livingModule.addRoom(new RocketRoom("кабина для космонавтов"));
        livingModule.addRoom(new RocketRoom("запасы продовольствия"));
        livingModule.addRoom(new RocketRoom("приборы управления"));
        rocket.addModule(livingModule);
        Human znayka = new Human("Знайка", new Location("комната", earth));
        
        Human zvezdochkin = new Human("профессор Звездочкин", new Location(earth));
        Human.Hypothesis hypothesis = new Human.Hypothesis("гравитационная", 50);
        znayka.addHypothesis(hypothesis);
        zvezdochkin.addHypothesis(hypothesis);
        Human neznayka = new Human("Незнайка", moonLocation);
        neznayka.reduceMood(100);
        Human ponchik = new Human("Пончик", moonLocation);
        ponchik.reduceMood(100);
        Feeling impatience = new Feeling("нетерпение", 2);
        
        impatience.apply(znayka);
        impatience.apply(zvezdochkin);
        znayka.wantVisit(moonLocation);
        zvezdochkin.wantVisit(moonLocation);
        znayka.wantCheckHypothesis();
        zvezdochkin.wantCheckHypothesis();
        neznayka.isDepressed(true);
        ponchik.isDepressed(true);
        rocketParts.move(spaceCity);
        try {
            rocket.manage();
        } catch(RocketExplosionException ex) {
            Utils.print(ex.getMessage()+" Ущерб составляет "+ex.getLoss()+" денежных единиц");
        }
        znayka.commitParticipation(rocket);
        zvezdochkin.commitParticipation(rocket);
        
        znayka.look(rocket);
        rocket.describe();
        rocket.beforeLaunchCheck(true);
        
        Utils.print("");
        // SECOND PART
        Human spruts = new Human("господин Спрутс", moonLocation);
        spruts.reduceKarma(30);
        Community hugePlants = new Community("гиганстких растений", 5);
        PoorStratum poorStratum = new PoorStratum("бедняки", moon, 100000);
        RichStratum richStratum = new RichStratum("богачи", moon, 100);
        Citizens earthCitizens = new Citizens("жители", earth, Origin.RESIDENT);
        Community astronoms = new Community("лучшие лунные астрономы", 20, 70);
        Seed seeds = new Seed(1);
        
        SpaceObject smallSpaceObject = new SpaceObject("космическое тело", 1);
        GravitonTelescope gravitonTelescope = new GravitonTelescope("гравитонный телескоп", 50);
        GravitonLocator gravitonLocator = new GravitonLocator("гравитонный локатор", 50);
        gravitonLocator.setLocation(moonLocation);
        
        hugePlants.destroy(spruts);
        (new Feeling("облегчение", 5)).apply(spruts);
        (new Feeling("уверенность", 7)).apply(spruts);
        poorStratum.setDependence(richStratum);
        poorStratum.addBan(seeds, moon);
        spruts.thinkProcess(5);
        earthCitizens.canSendSpaceship(rocket, moon);
        spruts.notPermitTo(moonLocation, earthCitizens, seeds);
        spruts.callCommunity(astronoms);
        astronoms.analyze(rocket, new AstronomicalInstruments(30));
        
        gravitonTelescope.isObservable(smallSpaceObject, true);
        gravitonLocator.canPredict(rocket, true);
    }
}
