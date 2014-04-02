package com.common.map.tiles;

import java.io.Serializable;

import com.common.movable.Movable;

public class Tile extends Movable implements Serializable {
	private static final long serialVersionUID = 1L;

	public boolean canStepOn = true;
	public boolean climable = false;
	public boolean blocksVision = false;

	public transient int darkness = 0;// used for rendering night

	public void clicked() {
	}

	@Override
	public String toString() {
		return "Tile [icon=" + icon + ", x=" + x + ", y=" + y + "]";
	}

	@Override
	public void Entered(Movable M) {
	}

	@Override
	public void Exited(Movable M) {
	}

	@Override
	public void Bump(Movable M) {
	}

	public static Tile getTile(String n) {
		try {
			ClassLoader cl = com.common.map.tiles.Tile.class.getClassLoader();
			Class<?> classDefinition = Class.forName("com.common.map.tiles." + n,
					false, cl);
			Object object = classDefinition.newInstance();
			return (Tile) object;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}

class Beach extends Tile {
	static final long serialVersionUID = 1L;

	public Beach() {
		setIcon("/tiles/beach.png");
	}
}

class Beachn extends Tile {
	static final long serialVersionUID = 1L;

	public Beachn() {
		setIcon("/tiles/beachn.png");
	}
}

class Beachs extends Tile {
	static final long serialVersionUID = 1L;

	public Beachs() {
		setIcon("/tiles/beachs.png");
		canStepOn = false;
	}
}

class Beacon extends Tile {
	static final long serialVersionUID = 1L;

	public Beacon() {
		setIcon("/tiles/beacon.png");
		canStepOn = false;
	}
}

class Bookcase extends Tile {
	static final long serialVersionUID = 1L;

	public Bookcase() {
		setIcon("/tiles/bookcase.png");
		canStepOn = false;
		blocksVision = true;
	}
}

class Bridge extends Tile {
	static final long serialVersionUID = 1L;

	public Bridge() {
		setIcon("/tiles/bridge.png");
	}
}

class Bridgen extends Tile {
	static final long serialVersionUID = 1L;

	public Bridgen() {
		setIcon("/tiles/bridgen.png");
	}
}

class Bridges extends Tile {
	static final long serialVersionUID = 1L;

	public Bridges() {
		setIcon("/tiles/bridges.png");
		canStepOn = false;
	}
}

class Burrow extends Tile {
	static final long serialVersionUID = 1L;

	public Burrow() {
		setIcon("/tiles/burrow.png");
	}
}

class Burrowclosed extends Tile {
	static final long serialVersionUID = 1L;

	public Burrowclosed() {
		setIcon("/tiles/burrowclosed.png");
	}
}

class Burrowcounter extends Tile {
	static final long serialVersionUID = 1L;

	public Burrowcounter() {
		setIcon("/tiles/burrowcounter.png");
		canStepOn = false;
	}
}

class Burrowfloor extends Tile {
	static final long serialVersionUID = 1L;

	public Burrowfloor() {
		setIcon("/tiles/burrowfloor.png");
	}
}

class Burrowladder extends Tile {
	static final long serialVersionUID = 1L;

	public Burrowladder() {
		setIcon("/tiles/burrowladder.png");
	}
}

class Burrowwall extends Tile {
	static final long serialVersionUID = 1L;

	public Burrowwall() {
		setIcon("/tiles/burrowwall.png");
		canStepOn = false;
		blocksVision = true;
	}
}

class Bush extends Tile {
	static final long serialVersionUID = 1L;

	public Bush() {
		setIcon("/tiles/bush.png");
		canStepOn = false;
	}
}

class Cactus extends Tile {
	static final long serialVersionUID = 1L;

	public Cactus() {
		setIcon("/tiles/cactus.png");
		canStepOn = false;
	}
}

class Deadtree extends Tile {
	static final long serialVersionUID = 1L;

	public Deadtree() {
		setIcon("/tiles/deadtree.png");
		canStepOn = false;
		blocksVision = true;
	}
}

class Desert extends Tile {
	static final long serialVersionUID = 1L;

	public Desert() {
		setIcon("/tiles/desert.png");
	}
}

class Door extends Tile {
	static final long serialVersionUID = 1L;

	public Door() {
		setIcon("/tiles/door.png");
		canStepOn = false;
		blocksVision = true;
	}
}

class Dropslot extends Tile {
	static final long serialVersionUID = 1L;

	public Dropslot() {
		canStepOn = false;
		setIcon("/tiles/dropslot.png");
	}
}

class Fountain extends Tile {
	static final long serialVersionUID = 1L;

	public Fountain() {
		setIcon("/tiles/fountain.png");
	}
}

class Grass extends Tile {
	static final long serialVersionUID = 1L;

	public Grass() {
		setIcon("/tiles/grass.png");
	}
}

class Grassdamaged extends Tile {
	static final long serialVersionUID = 1L;

	public Grassdamaged() {
		setIcon("/tiles/grassdamaged.png");
	}
}

class Grasshill extends Tile {
	static final long serialVersionUID = 1L;

	public Grasshill() {
		setIcon("/tiles/grasshill.png");
	}
}

class Grassleaf extends Tile {
	static final long serialVersionUID = 1L;

	public Grassleaf() {
		setIcon("/tiles/grassleaf.png");
	}
}

class Grassleafe extends Tile {
	static final long serialVersionUID = 1L;

	public Grassleafe() {
		setIcon("/tiles/grassleafe.png");
	}
}

class Grassleafn extends Tile {
	static final long serialVersionUID = 1L;

	public Grassleafn() {
		setIcon("/tiles/grassleafn.png");
	}
}

class Grassleafne extends Tile {
	static final long serialVersionUID = 1L;

	public Grassleafne() {
		setIcon("/tiles/grassleafne.png");
	}
}

class Grassleafnw extends Tile {
	static final long serialVersionUID = 1L;

	public Grassleafnw() {
		setIcon("/tiles/grassleafnw.png");
	}
}

class Grassleafo extends Tile {
	static final long serialVersionUID = 1L;

	public Grassleafo() {
		setIcon("/tiles/grassleafo.png");
	}
}

class Grassleafr extends Tile {
	static final long serialVersionUID = 1L;

	public Grassleafr() {
		setIcon("/tiles/grassleafr.png");
	}
}

class Grassleafs extends Tile {
	static final long serialVersionUID = 1L;

	public Grassleafs() {
		setIcon("/tiles/grassleafs.png");
	}
}

class Grassleafse extends Tile {
	static final long serialVersionUID = 1L;

	public Grassleafse() {
		setIcon("/tiles/grassleafse.png");
	}
}

class Grassleafsw extends Tile {
	static final long serialVersionUID = 1L;

	public Grassleafsw() {
		setIcon("/tiles/grassleafsw.png");
	}
}

class Grassleafw extends Tile {
	static final long serialVersionUID = 1L;

	public Grassleafw() {
		setIcon("/tiles/grassleafw.png");
	}
}

class Grassleafx extends Tile {
	static final long serialVersionUID = 1L;

	public Grassleafx() {
		setIcon("/tiles/grassleafx.png");
	}
}

class Grasslogs extends Tile {
	static final long serialVersionUID = 1L;

	public Grasslogs() {
		setIcon("/tiles/grasslogs.png");
	}
}

class Hedgehogstatue extends Tile {
	static final long serialVersionUID = 1L;

	public Hedgehogstatue() {
		canStepOn = false;
		setIcon("/tiles/hedgehogstatue.png");
	}
}

class Mountain extends Tile {
	static final long serialVersionUID = 1L;

	public Mountain() {
		setIcon("/tiles/mountain.png");
		canStepOn = false;
	}
}

class Mountains extends Tile {
	static final long serialVersionUID = 1L;

	public Mountains() {
		setIcon("/tiles/mountains.png");
		canStepOn = false;
	}
}

class Mountainse extends Tile {
	static final long serialVersionUID = 1L;

	public Mountainse() {
		setIcon("/tiles/mountainse.png");
		canStepOn = false;
	}
}

class Mountainsw extends Tile {
	static final long serialVersionUID = 1L;

	public Mountainsw() {
		setIcon("/tiles/mountainsw.png");
		canStepOn = false;
	}
}

class Mountaintope extends Tile {
	static final long serialVersionUID = 1L;

	public Mountaintope() {
		setIcon("/tiles/mountaintope.png");
		canStepOn = false;
	}
}

class Mountaintopn extends Tile {
	static final long serialVersionUID = 1L;

	public Mountaintopn() {
		setIcon("/tiles/mountaintopn.png");
		canStepOn = false;
	}
}

class Mountaintopne extends Tile {
	static final long serialVersionUID = 1L;

	public Mountaintopne() {
		setIcon("/tiles/mountaintopne.png");
		canStepOn = false;
	}
}

class Mountaintopnw extends Tile {
	static final long serialVersionUID = 1L;

	public Mountaintopnw() {
		setIcon("/tiles/mountaintopnw.png");
		canStepOn = false;
	}
}

class Mountaintops extends Tile {
	static final long serialVersionUID = 1L;

	public Mountaintops() {
		setIcon("/tiles/mountaintops.png");
		canStepOn = false;
	}
}

class Mountaintopse extends Tile {
	static final long serialVersionUID = 1L;

	public Mountaintopse() {
		setIcon("/tiles/mountaintopse.png");
		canStepOn = false;
	}
}

class Mountaintopsw extends Tile {
	static final long serialVersionUID = 1L;

	public Mountaintopsw() {
		setIcon("/tiles/mountaintopsw.png");
		canStepOn = false;
	}
}

class Mountaintopw extends Tile {
	static final long serialVersionUID = 1L;

	public Mountaintopw() {
		setIcon("/tiles/mountaintopw.png");
		canStepOn = false;
	}
}

class Mousestatue extends Tile {
	static final long serialVersionUID = 1L;

	public Mousestatue() {
		setIcon("/tiles/mousestatue.png");
		canStepOn = false;
	}
}

class Pillar extends Tile {
	static final long serialVersionUID = 1L;

	public Pillar() {
		setIcon("/tiles/pillar.png");
		canStepOn = false;
	}
}

class Pinetree extends Tile {
	static final long serialVersionUID = 1L;

	public Pinetree() {
		setIcon("/tiles/pinetree.png");
		canStepOn = false;
		climable = true;
		blocksVision = true;
	}
}

class Road extends Tile {
	static final long serialVersionUID = 1L;

	public Road() {
		setIcon("/tiles/road.png");
	}
}

class Roade extends Tile {
	static final long serialVersionUID = 1L;

	public Roade() {
		setIcon("/tiles/roade.png");
	}
}

class Roadn extends Tile {
	static final long serialVersionUID = 1L;

	public Roadn() {
		setIcon("/tiles/roadn.png");
	}
}

class Roadne extends Tile {
	static final long serialVersionUID = 1L;

	public Roadne() {
		setIcon("/tiles/roadne.png");
	}
}

class Roadnec extends Tile {
	static final long serialVersionUID = 1L;

	public Roadnec() {
		setIcon("/tiles/roadnec.png");
	}
}

class Roadnw extends Tile {
	static final long serialVersionUID = 1L;

	public Roadnw() {
		setIcon("/tiles/roadnw.png");
	}
}

class Roadnwc extends Tile {
	static final long serialVersionUID = 1L;

	public Roadnwc() {
		setIcon("/tiles/roadnwc.png");
	}
}

class Roado extends Tile {
	static final long serialVersionUID = 1L;

	public Roado() {
		setIcon("/tiles/roado.png");
	}
}

class Roads extends Tile {
	static final long serialVersionUID = 1L;

	public Roads() {
		setIcon("/tiles/roads.png");
	}
}

class Roadse extends Tile {
	static final long serialVersionUID = 1L;

	public Roadse() {
		setIcon("/tiles/roadse.png");
	}
}

class Roadsec extends Tile {
	static final long serialVersionUID = 1L;

	public Roadsec() {
		setIcon("/tiles/roadsec.png");
	}
}

class Roadsw extends Tile {
	static final long serialVersionUID = 1L;

	public Roadsw() {
		setIcon("/tiles/roadsw.png");
	}
}

class Roadswc extends Tile {
	static final long serialVersionUID = 1L;

	public Roadswc() {
		setIcon("/tiles/roadswc.png");
	}
}

class Roadw extends Tile {
	static final long serialVersionUID = 1L;

	public Roadw() {
		setIcon("/tiles/roadw.png");
	}
}

class Roadx extends Tile {
	static final long serialVersionUID = 1L;

	public Roadx() {
		setIcon("/tiles/roadx.png");
	}
}

class Rockcopper extends Tile {
	static final long serialVersionUID = 1L;

	public Rockcopper() {
		canStepOn = false;
		setIcon("/tiles/rockcopper.png");
	}
}

class Rockgold extends Tile {
	static final long serialVersionUID = 1L;

	public Rockgold() {
		canStepOn = false;
		setIcon("/tiles/rockgold.png");
	}
}

class Rockiron extends Tile {
	static final long serialVersionUID = 1L;

	public Rockiron() {
		canStepOn = false;
		setIcon("/tiles/rockiron.png");
	}
}

class Rockmagite extends Tile {
	static final long serialVersionUID = 1L;

	public Rockmagite() {
		setIcon("/tiles/rockmagite.png");
		canStepOn = false;
	}
}

class Rocksilver extends Tile {
	static final long serialVersionUID = 1L;

	public Rocksilver() {
		setIcon("/tiles/rocksilver.png");
		canStepOn = false;
	}
}

class Rocktin extends Tile {
	static final long serialVersionUID = 1L;

	public Rocktin() {
		setIcon("/tiles/rocktin.png");
		canStepOn = false;
	}
}

class Roof extends Tile {
	static final long serialVersionUID = 1L;

	public Roof() {
		setIcon("/tiles/roof.png");
		canStepOn = false;
		blocksVision = true;
	}
}

class Shrewstatue extends Tile {
	static final long serialVersionUID = 1L;

	public Shrewstatue() {
		setIcon("/tiles/shrewstatue.png");
	}
}

class Stairs extends Tile {
	static final long serialVersionUID = 1L;

	public Stairs() {
		setIcon("/tiles/stairs.png");
	}
}

class Stonefloor extends Tile {
	static final long serialVersionUID = 1L;

	public Stonefloor() {
		setIcon("/tiles/stonefloor.png");
	}
}

class Swamp extends Tile {
	static final long serialVersionUID = 1L;

	public Swamp() {
		setIcon("/tiles/swamp.png");
	}
}

class Swampe extends Tile {
	static final long serialVersionUID = 1L;

	public Swampe() {
		setIcon("/tiles/swampe.png");
	}
}

class Swampn extends Tile {
	static final long serialVersionUID = 1L;

	public Swampn() {
		setIcon("/tiles/swampn.png");
	}
}

class Swampne extends Tile {
	static final long serialVersionUID = 1L;

	public Swampne() {
		setIcon("/tiles/swampne.png");
	}
}

class Swampnec extends Tile {
	static final long serialVersionUID = 1L;

	public Swampnec() {
		setIcon("/tiles/swampnec.png");
	}
}

class Swampnw extends Tile {
	static final long serialVersionUID = 1L;

	public Swampnw() {
		setIcon("/tiles/swampnw.png");
	}
}

class Swampnwc extends Tile {
	static final long serialVersionUID = 1L;

	public Swampnwc() {
		setIcon("/tiles/swampnwc.png");
	}
}

class Swampo extends Tile {
	static final long serialVersionUID = 1L;

	public Swampo() {
		setIcon("/tiles/swampo.png");
	}
}

class Swamps extends Tile {
	static final long serialVersionUID = 1L;

	public Swamps() {
		setIcon("/tiles/swamps.png");
	}
}

class Swampse extends Tile {
	static final long serialVersionUID = 1L;

	public Swampse() {
		setIcon("/tiles/swampse.png");
	}
}

class Swampsec extends Tile {
	static final long serialVersionUID = 1L;

	public Swampsec() {
		setIcon("/tiles/swampsec.png");
	}
}

class Swampsw extends Tile {
	static final long serialVersionUID = 1L;

	public Swampsw() {
		setIcon("/tiles/swampsw.png");
	}
}

class Swampswc extends Tile {
	static final long serialVersionUID = 1L;

	public Swampswc() {
		setIcon("/tiles/swampswc.png");
	}
}

class Swamptree extends Tile {
	static final long serialVersionUID = 1L;

	public Swamptree() {
		setIcon("/tiles/swamptree.png");
		canStepOn = false;
		blocksVision = true;
	}
}

class Swampw extends Tile {
	static final long serialVersionUID = 1L;

	public Swampw() {
		setIcon("/tiles/swampw.png");
	}
}

class Swampx extends Tile {
	static final long serialVersionUID = 1L;

	public Swampx() {
		setIcon("/tiles/swampx.png");
	}
}

class Table extends Tile {
	static final long serialVersionUID = 1L;

	public Table() {
		setIcon("/tiles/table.png");
		canStepOn = false;
	}
}

class Tablee extends Tile {
	static final long serialVersionUID = 1L;

	public Tablee() {
		setIcon("/tiles/tablee.png");
		canStepOn = false;
	}
}

class Tablew extends Tile {
	static final long serialVersionUID = 1L;

	public Tablew() {
		setIcon("/tiles/tablew.png");
		canStepOn = false;
	}
}

class Tree extends Tile {
	static final long serialVersionUID = 1L;

	public Tree() {
		setIcon("/tiles/tree.png");
		canStepOn = false;
		climable = true;
		blocksVision = true;
	}
}

class Treestump extends Tile {
	static final long serialVersionUID = 1L;

	public Treestump() {
		setIcon("/tiles/treestump.png");
	}
}

class Wall extends Tile {
	static final long serialVersionUID = 1L;

	public Wall() {
		setIcon("/tiles/wall.png");
		canStepOn = false;
		blocksVision = true;
	}
}

class Water extends Tile {
	static final long serialVersionUID = 1L;

	public Water() {
		setIcon("/tiles/water.png");
		canStepOn = false;
	}
}

class Watere extends Tile {
	static final long serialVersionUID = 1L;

	public Watere() {
		setIcon("/tiles/watere.png");
		canStepOn = false;
	}
}

class Watern extends Tile {
	static final long serialVersionUID = 1L;

	public Watern() {
		setIcon("/tiles/watern.png");
		canStepOn = false;
	}
}

class Waterne extends Tile {
	static final long serialVersionUID = 1L;

	public Waterne() {
		setIcon("/tiles/waterne.png");
		canStepOn = false;
	}
}

class Waternec extends Tile {
	static final long serialVersionUID = 1L;

	public Waternec() {
		setIcon("/tiles/waternec.png");
		canStepOn = false;
	}
}

class Waternw extends Tile {
	static final long serialVersionUID = 1L;

	public Waternw() {
		setIcon("/tiles/waternw.png");
		canStepOn = false;
	}
}

class Waternwc extends Tile {
	static final long serialVersionUID = 1L;

	public Waternwc() {
		setIcon("/tiles/waternwc.png");
		canStepOn = false;
	}
}

class Waters extends Tile {
	static final long serialVersionUID = 1L;

	public Waters() {
		setIcon("/tiles/waters.png");
		canStepOn = false;
	}
}

class Waterse extends Tile {
	static final long serialVersionUID = 1L;

	public Waterse() {
		setIcon("/tiles/waterse.png");
		canStepOn = false;
	}
}

class Watersec extends Tile {
	static final long serialVersionUID = 1L;

	public Watersec() {
		setIcon("/tiles/watersec.png");
		canStepOn = false;
	}
}

class Watersw extends Tile {
	static final long serialVersionUID = 1L;

	public Watersw() {
		setIcon("/tiles/watersw.png");
		canStepOn = false;
	}
}

class Waterswc extends Tile {
	static final long serialVersionUID = 1L;

	public Waterswc() {
		setIcon("/tiles/waterswc.png");
		canStepOn = false;
	}
}

class Waterw extends Tile {
	static final long serialVersionUID = 1L;

	public Waterw() {
		setIcon("/tiles/waterw.png");
		canStepOn = false;
	}
}

class Woodfloor extends Tile {
	static final long serialVersionUID = 1L;

	public Woodfloor() {
		setIcon("/tiles/woodfloor.png");
	}
}
