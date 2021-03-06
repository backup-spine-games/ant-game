package main.assetmanager;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.assets.loaders.FileHandleResolver;
import com.badlogic.gdx.assets.loaders.resolvers.InternalFileHandleResolver;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;

public class AssetManagerManager {
	private AssetManager assetManager;
	private FileHandleResolver resolver;

	public AssetManagerManager() {
		assetManager = new AssetManager();
		resolver = new InternalFileHandleResolver();
		loadAll();
		assetManager.finishLoading();
	}

	public void loadAll() {
		load("sounds", Sound.class);
		load("music", Music.class);
		load("atlas", TextureAtlas.class);
	}

	public void load(String directory, Class<?> classType) {
		FileHandle folder = resolver.resolve("").child("assets/" + directory);
		for (FileHandle asset : folder.list()) {
			assetManager.load(asset.path(), classType);
		}
	}

	public Sound getSound(String name) {
		return assetManager.get("assets/sounds/" + name + ".mp3", Sound.class);
	}

	public Music getMusic(String name) {
		return Gdx.audio.newMusic(Gdx.files.internal("assets/music/" + name + ".mp3"));
		// note: loading with the asset manager makes playing multiple of the same music
		// file impossible. For now, we will use the above method for loading because it
		// does not have that limitation
		// return assetManager.get("assets/music/" + name + ".mp3", Music.class);
	}

	public Texture getTexture(String name) {
		return new Texture("assets/textures/" + name + ".png");
	}

	public TextureAtlas getTextureAtlas() {
		return assetManager.get("assets/atlas/atlas.atlas", TextureAtlas.class);
	}

	public void dispose() {
		assetManager.dispose();
	}
}
