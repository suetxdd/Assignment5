package simpledatabase;
import java.util.ArrayList;

public class Join extends Operator{

	private ArrayList<Attribute> newAttributeList;
	private String joinPredicate;
	ArrayList<Tuple> tuples1;

	
	//Join Constructor, join fill
	public Join(Operator leftChild, Operator rightChild, String joinPredicate){
		this.leftChild = leftChild;
		this.rightChild = rightChild;
		this.joinPredicate = joinPredicate;
		newAttributeList = new ArrayList<Attribute>();
		tuples1 = new ArrayList<Tuple>();
		
	}

	
	/**
     * It is used to return a new tuple which is already joined by the common attribute
     * @return the new joined tuple
     */
	@Override
	public Tuple next(){
		Tuple left;
		Tuple right;
	
		while((left = leftChild.next()) != null){
			tuples1.add(left);
		}
	
		while((right = rightChild.next()) != null){
			for (int i = 0; i < tuples1.size(); i++){
				left = tuples1.get(i);
			
				for(int j = 0;j < right.getAttributeList().size();j++){
					for(int k = 0;k < left.getAttributeList().size();k++){
						if(right.getAttributeName(j).equals(left.getAttributeName(k))){
							if(right.getAttributeValue(j).equals(left.getAttributeValue(k))){
								newAttributeList = new ArrayList<Attribute>();
								for(int l = 0; l <left.getAttributeList().size() ;l++){
									newAttributeList.add(left.getAttributeList().get(l));
								}
								for(int m = 0; m <right.getAttributeList().size() ;m++){
									if(m != j)
										newAttributeList.add(right.getAttributeList().get(m));
								}
								return new Tuple(newAttributeList);
							}
						}
					}
				}
			}
		}
		return null;
	}
	
	
	/**
     * The function is used to get the attribute list of the tuple
     * @return attribute list
     */
	public ArrayList<Attribute> getAttributeList(){
		if(joinPredicate.isEmpty())
			return child.getAttributeList();
		else
			return(newAttributeList);
	}

}