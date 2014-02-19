package assignment3.objects;

import com.jme3.asset.AssetManager;
import com.jme3.effect.ParticleEmitter;
import com.jme3.effect.ParticleMesh;
import com.jme3.light.PointLight;
import com.jme3.material.Material;
import com.jme3.math.ColorRGBA;
import com.jme3.math.Vector3f;
import com.jme3.scene.Node;
import com.jme3.scene.Spatial;

public class Torch {
	
	private String torchMaterialDefinition = "Common/MatDefs/Light/Lighting.j3md";
	private String diffuseMap = "Models/Torch/pdtexture.jpg";
	private String normalMap = "Models/Torch/normal_map2.png";
	private String alphaMap = "Models/Torch/alpha_map4.png";
	
	private String fireMaterialDefinition = "Common/MatDefs/Misc/Particle.j3md";
	private String fireTexture = "Effects/Explosion/flame.png";
	
	private Node torchNode;
	private Spatial torch;
	private ParticleEmitter fire; 
	private PointLight pointLight;
	
	public Torch(String name, AssetManager assetManager)	{
		torchNode = new Node(name);
		
		torch = assetManager.loadModel("Models/Torch/Cylinder.001.mesh.xml");
		torch.setLocalScale(0.2f, 0.2f, 0.2f);
		Material torchMaterial = new Material(assetManager, torchMaterialDefinition);
		torchMaterial.setTexture("DiffuseMap", assetManager.loadTexture(diffuseMap));
		torchMaterial.setTexture("NormalMap", assetManager.loadTexture(normalMap));
		torchMaterial.setTexture("AlphaMap", assetManager.loadTexture(alphaMap));
		torch.setMaterial(torchMaterial);
		torchNode.attachChild(torch);
		
		fire = new ParticleEmitter("Emitter", ParticleMesh.Type.Triangle, 30);
		Material mat_red = new Material(assetManager, fireMaterialDefinition);
	    mat_red.setTexture("Texture", assetManager.loadTexture(fireTexture));
	    fire.setMaterial(mat_red);
	    fire.setImagesX(2); 
	    fire.setImagesY(2); // 2x2 texture animation
	    fire.setEndColor(  new ColorRGBA(1f, 0f, 0f, 1f));   // red
	    fire.setStartColor(new ColorRGBA(1f, 1f, 0f, 0.5f)); // yellow
	    fire.getParticleInfluencer().setInitialVelocity(new Vector3f(0, 2, 0));
	    fire.setStartSize(1.5f);
	    fire.setEndSize(0.1f);
	    fire.setGravity(0, 0, 0);
	    fire.setLowLife(1f);
	    fire.setHighLife(3f);
	    fire.getParticleInfluencer().setVelocityVariation(0.3f);
	    fire.setLocalTranslation(0, 1.5f, 0.5f);
	    torchNode.attachChild(fire);
	    
	    pointLight = new PointLight();
	    pointLight.setColor(new ColorRGBA(1.0f, 0.6f, 0, 1.0f));
//	    pointLight.setColor(ColorRGBA.Yellow);
	    pointLight.setRadius(60f);
	    pointLight.setPosition(new Vector3f(0, 1.5f, 0.5f));
	    torchNode.addLight(pointLight);
	}
	
	public void translate(float x, float y, float z)	{
		pointLight.setPosition(new Vector3f(x+2, y+2, z+2));
		torchNode.setLocalTranslation(x, y, z);
	}
	
	public void translate(Vector3f location)	{
		torchNode.setLocalTranslation(location);
	}
	
	public PointLight getLight()	{
		return pointLight;
	}
	
	public Node getTorchNode()	{
		return torchNode;
	}
}
