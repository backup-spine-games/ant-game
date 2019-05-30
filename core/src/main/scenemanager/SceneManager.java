package main.scenemanager;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

import main.floors.Floor;
import main.main.Globals;

public class SceneManager {

	private Stage stage;

	private Table rootTable;

	private Table gameplayButtonTable;

	private Table settingsTable;
	private Table designViewTable;

	private TextureAtlas atlas;
	private Skin skin;

	private Floor floor;

	public SceneManager(Stage stage, Floor floor) {
		atlas = new TextureAtlas("assets/skinTest.atlas");
		skin = new Skin(new FileHandle("assets/skinTest.json"), atlas);

		this.floor = floor;

		this.stage = stage;

		// Gdx.input.setInputProcessor(stage);

		rootTable = new Table(skin);
		gameplayButtonTable = new Table();
		settingsTable = new Table(skin);
		designViewTable = new Table(skin);

		initializeGameplayButtonTable();
		initializeSettingsTable();
		initializeDesignViewTable();

		rootTable.setFillParent(true);
		stage.addActor(rootTable);
	}

	public void render() {
		stage.act(Gdx.graphics.getDeltaTime());
		stage.draw();
	}

	private void initializeGameplayButtonTable() {
		Button settingsButton = new Button(skin, "optionsButton");
		Button designViewButton = new Button(skin, "designViewButton");

		settingsButton.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				rootTable.clearChildren();
				rootTable.addActor(settingsTable);
			};
		});

		designViewButton.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				rootTable.clearChildren();
				rootTable.addActor(designViewTable);
			};
		});

		Table antTable = new Table();

		antTable.add(new Button(skin)).width(75).height(75).right().expandX();
		antTable.row();
		antTable.add(new Button(skin)).width(75).height(75).right();
		antTable.row();
		antTable.add(new Button(skin)).width(75).height(75).right();
		antTable.row();
		antTable.add(new Button(skin)).width(75).height(75).right();
		antTable.row();
		antTable.add(new Button(skin)).width(75).height(75).right();
		antTable.row();
		antTable.add(new Button(skin)).width(75).height(75).right();

		gameplayButtonTable.add(settingsButton).left().top().width(75).height(75);

		gameplayButtonTable.row().width(Globals.windowWidth).height(Globals.windowHeight - 150);
		gameplayButtonTable.add(antTable);
		gameplayButtonTable.row();

		gameplayButtonTable.add(designViewButton).bottom().right().width(75).height(75);

		rootTable.add(gameplayButtonTable);
	}

	private void initializeSettingsTable() {
		settingsTable.setFillParent(true);
		settingsTable.top().left();
		settingsTable.setDebug(true);

		Button backButton = new Button(skin, "backButton");

		backButton.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				rootTable.clearChildren();
				rootTable.addActor(gameplayButtonTable);
			};
		});

		settingsTable.setBackground("transparentBackground");
		settingsTable.add(backButton);
	}

	private void initializeDesignViewTable() {
		designViewTable.setFillParent(true);
		designViewTable.top().left();
		designViewTable.setDebug(true);

		Button backButton = new Button(skin, "backButton");
		Button saveButton = new Button(skin, "saveButton");
		Button loadButton = new Button(skin, "loadButton");

		backButton.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				rootTable.clearChildren();
				rootTable.addActor(gameplayButtonTable);
			};
		});

		designViewTable.setBackground("transparentBackground");

		designViewTable.add(backButton).padRight(150);

		designViewTable.add(saveButton);
		designViewTable.add(loadButton);

	}
}
