package assignment4;

import assignment4.objects.Torch;

import com.jme3.scene.Node;
import com.jme3.scene.Spatial;

public class LightController {

	private Node rootNode, torchesNode = new Node();
	
	public LightController(Node rootNode, Node torchesNode) {
		this.rootNode = rootNode;
		this.torchesNode = torchesNode;
	}
	
	public void addTorches(Torch... torchArray)	{
		for(Torch torch : torchArray)
			torchesNode.attachChild(torch);
	}
	
	public void removeTroches(Torch... torchArray)	{
		for(Torch torch : torchArray)
			torchesNode.detachChild(torch);
	}
	
	public Node getTorchesNode()	{
		return torchesNode;
	}
	
	public void turnOffTorch(Torch torch)	{
		rootNode.removeLight(torch.getLight());
		torch.turnOff();
	}
	
	public void turnOffTorch(String torchName)	{
		for(Spatial spatial : torchesNode.getChildren())		{
			if(spatial.getName().equals(torchName))		{
				turnOffTorch((Torch) spatial);
			}
		}
	}
	
	public void turnOnTorch(Torch torch)	{
		rootNode.addLight(torch.getLight());
		torch.turnOn();
	}
	
	public void turnOnTorch(String torchName)	{
		for(Spatial spatial : torchesNode.getChildren())		{
			if(spatial.getName().equals(torchName))		{
				turnOnTorch((Torch) spatial);
			}
		}
	}
	
	public void turnTorchLightSwitch(Torch torch)	{
		if(torch.isOn())	{
			turnOffTorch(torch);
		}
		else	{
			turnOnTorch(torch);
		}
	}
	
	public void turnTorchLightSwitch(String torchName)	{
		for(Spatial spatial : torchesNode.getChildren())	{
			if(spatial.getName().equals(torchName))		{
				turnTorchLightSwitch((Torch) spatial);
			}
		}
	}
}
