package net.sourceforge.plantuml.golem;

import static org.junit.jupiter.api.Assertions.*;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import net.sourceforge.plantuml.golem.TilesField;


/**
 * Test suite for the TilesField class.
 */
public class TestTilesField {
    private TilesField tilesField = new TilesField();

    @BeforeEach
    public void resetSharedState() {
        tilesField = null;
    }

    // ============================================================================================
    // createTile tests
    // ============================================================================================

    @Test
    public void testCreateTileNorthPosition() {
    		Tile start = tilesField.getRoot();
        TileGeometry startDirection = TileGeometry.NORTH;
        tilesField.createTile(start, startDirection);
    }

    @Test
    public void testCreateTileInvalidPosition() {
    		assertThrows(NullPointerException.class, tilesField.createTile(tilesField.getRoot(), null);
    }

    @Test
    public void testCreateTileOccupiedPosition() {
				Tile start = TilesField.getRoot();
				TilesField.createTile(start, TileGeometry.NORTH);
				assertDoesNotThrow(TilesField.createTile(start, TileGeometry.NORTH);
    }

    @Test
    public void testCreateTileInvalidTile() {
    		TileGeometry startGeom = TileGeometry.NORTH;
    		assertThrows(IllegalArgumentException.class, tilesField.createTile(Tile(15), startGeom));
    }

    @Test
    public void testCreateTileNullTile() {
				assertThrows(NullPointerException.class, tilesFields.createTile(TilesField.getRoot(), TileGeometry.NORTH));
    }

    // ============================================================================================
    // addPath tests
    // ============================================================================================

    @Test
    public void testPathAdded() {
        tilesField = new TilesField();
        // setup positions map
        Tile start = tilesField.getRoot();
        Tile dest = new Tile(1);
        TileGeometry startDirection = TileGeometry.EAST;
        tilesField.createTile(start, startDirection);

        tilesField.addPath(start, dest, startDirection);
    }

    @Test
    public void testThrowsForNullStart() {
        tilesField = new TilesField();
    }

    @Test
    public void testThrowsForNullDestination() {
        tilesField = new TilesField();
    }

    @Test
    public void testThrowsForNullStartDirection() {
        tilesField = new TilesField();
    }

    @Test
    public void testThrowsForInvalidPath() {
        tilesField = new TilesField();
    }

    // ============================================================================================
    // buildPath tests
    // ============================================================================================

    private static Method getBuildPathFn() {
        Class<?> tilesField = TilesField.class;
        try {
            // get private method
            Method buildPath = tilesField.getDeclaredMethod("buildPath", TileArea.class, TileArea.class);
            // make it public
            buildPath.setAccessible(true);
            return buildPath;
        } catch (NoSuchMethodException ex) {
            // error
        }
        throw new IllegalStateException();
    }

    private static Path buildPath(TilesField tsf, TileArea tileArea1, TileArea tileArea2) throws Throwable {
        Method buildPathFn = getBuildPathFn();
        try {
            return (Path) buildPathFn.invoke(tsf, tileArea1, tileArea2);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            // rethrow inner exception
            throw e.getTargetException();
        }
        throw new IllegalStateException(); // satisfy compiler
    }

    @Test
    public void testAdjacentTilesCreatePath() throws Throwable {
        tilesField = new TilesField();
        var tile1 = new Tile(0);
        var tile2 = new Tile(1);
        var tileArea1 = new TileArea(tile1, TileGeometry.EAST);
        var tileArea2 = new TileArea(tile2, TileGeometry.WEST);
        tilesField.addPosition(tile1, new Position(0, 0, 1, 1));
        tilesField.addPosition(tile2, new Position(2, 0, 3, 1));

        assertEquals(Path.build(tileArea1, tileArea2), buildPath(tilesField, tileArea1, tileArea2));
    }

    @Test
    public void testNonAdjacentTilesError() {
    }

    @Test
    public void testSameTileOppositeSidesCreatesPath() {
    }

    @Test
    public void nonAdjacentSpecialCaseCreatesPath() {
    }

    // ============================================================================================
    // isAdjoining tests
    // ============================================================================================

    private static Method getIsAdjoiningFn() {
        Class<?> tilesField = TilesField.class;
        try {
            // get private method
            Method isAdjoining = tilesField.getDeclaredMethod("isAdjoining");
            // make it public
            isAdjoining.setAccessible(true);
            return isAdjoining;
        } catch (NoSuchMethodException ex) {
            // error
        }
        throw new IllegalStateException();
    }

    private static boolean isAdjoining(TilesField tsf, TileArea tileArea1, TileArea tileArea2) {
        Method isAdjoiningFn = getIsAdjoiningFn();
        try {
            return (boolean) isAdjoiningFn.invoke(tileArea1, tileArea2);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        throw new IllegalStateException(); // satisfy compiler
    }

    @Test
    public void testIdenticalPosisiontsAndGeometriesError() {
    }

    @Test
    public void testIdenticalPositionsButNotGeometriesIsTrue() {
    }

    @Test
    public void testDifferentPositionsWithOppositesGoemetiresIsFalse() {
    }

    @Test
    public void testDifferentPostionsNotOppositeGeomsWithGeo1East() {
    }

    @Test
    public void testDifferentPostionsNotOppositeGeomsWithGeom1West() {
    }

    @Test
    public void testDifferentPostionsNotOppositeGeomsWithGeom1North() {
    }

    @Test
    public void testDifferentPostionsNotOppositeGeomsWithGeom1South() {
    }

    @Test
    public void testDifferentPostionsNotOppositeGeomsWithGeom1Center() {
    }

    @Test
    public void testDifferentPostionsNotOppositeGeomsWithGeom1Invalid() {
    }

    // ============================================================================================
    // getFreePosition tests
    // ============================================================================================

    private static Method getGetFreePositionFn() {
        Class<?> tilesField = TilesField.class;
        try {
            // get private method
            Method getFreePosition = tilesField.getDeclaredMethod("getFreePosition");
            // make it public
            getFreePosition.setAccessible(true);
            return getFreePosition;
        } catch (NoSuchMethodException ex) {
            // error
        }
        throw new IllegalStateException(); // satisfy compiler
    }

    private static Position getFreePosition(TilesField tsf, Tile start, TileGeometry position) {
        Method getFreePositionFn = getGetFreePositionFn();
        try {
            return (Position) getFreePositionFn.invoke(start, position);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        throw new IllegalStateException(); // satisfy compiler
    }

    @Test
    public void testMoveOnlyTile() {
    }

    @Test
    public void testMoveTileWithOneConflict() {
    }

    @Test
    public void testMoveTileWithTwoConfilcts() {
    }

    @Test
    public void testTileShouldNotMoveIfNotBlockingY() {
    }

    @Test
    public void testTileShouldNotMoveIfNotBlockingX() {
    }

    @Test
    public void testThrowsOnNullStart() {
    }

    @Test
    public void testThrowsOnNullPosition() {
    }

    @Test
    public void testThrowsOnCenterPosition() {
    }
}
