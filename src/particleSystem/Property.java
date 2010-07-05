package particleSystem;




public class Property {
	
	
	public final int index;
	public final String name;
	public int [][] affectionProps;
	
	public Property(int index, String name,int [][] affectionProps){
		this.index = index;
		this.name = name.toUpperCase();
		this.affectionProps = affectionProps;
	
		
	}

	
	
	
	/**
	 * @return the index
	 */
	public synchronized int getIndex() {
		return index;
	}

	/**
	 * @return the name
	 */
	public synchronized String getName() {
		return name;
	}

	/**
	 * @return the affectionProps
	 */
	public synchronized int[][] getAffectionProps() {
		return affectionProps;
	}

	/**
	 * @return all daytime values from the Class Property as a {@code float[]}
	 */
	public int[] getDayValues(){
		int [] dayValues = new int[]{
				affectionProps[0][0],
				affectionProps[0][1],
				affectionProps[0][2]};
		return dayValues;
	
	}
	/**
	 * @return all nitetime values from the Class Property as a {@code float[]}
	 */
	public int[] getNiteValues(){
		int [] dayValues = new int[]{
				affectionProps[1][0],
				affectionProps[1][1],
				affectionProps[1][2]};
		return dayValues;
	
	}
	
	/**
	 * 
	 * @param time day or nite represented by 0(as day) 1(as nite)
	 * @param space private public or work represented by 0(private) 1(public) 2(work)
	 * @return a {@code float} value
	 * @see {@link ../util/XMLImporter.html#ObjectPropertys(int id, XMLElement root)}
	 */
	public int valueByIndex(int time, int space){
		return affectionProps[time][space];
	}
	
	/**
	 * @param affectionProps the affectionProps to set
	 */
	public synchronized void setAffectionProps(int[][] affectionProps) {
		this.affectionProps = affectionProps;
	}

	

}
