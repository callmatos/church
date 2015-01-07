package org.church.ws;

/**
 * Hello world!
 *
 */
public class App 
{
	private String name;
	
	public App(String name){
		this.name = name;
	}
	
    @Override
    public String toString() {
    	
    	return super.toString()+name;
    }
}
