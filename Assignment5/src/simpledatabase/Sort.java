package simpledatabase;
import java.util.ArrayList;

public class Sort extends Operator{
	
	private ArrayList<Attribute> newAttributeList;
	private String orderPredicate;
	ArrayList<Tuple> tuplesResult;

	
	public Sort(Operator child, String orderPredicate){
		this.child = child;
		this.orderPredicate = orderPredicate;
		newAttributeList = new ArrayList<Attribute>();
		tuplesResult = new ArrayList<Tuple>();
		
	}
	
	
	/**
     * The function is used to return the sorted tuple
     * @return tuple
     */
	@Override
	public Tuple next() {
		if (tuplesResult.size() == 0) {
			Tuple tuple;
			ArrayList<Tuple> temp = new ArrayList<Tuple>();
			while ((tuple = child.next())!= null) {
				temp.add(tuple);
			}
			if(temp.size() == 0)
				return null;
			tuple = temp.get(0);
			int i;
			for(i = 0; i < tuple.getAttributeList().size(); i++){
				if(tuple.getAttributeName(i).equals(orderPredicate))
					break;
			}
			while(temp.size() > 0){
				int minTuple = 0;
				for(int j = 0; j < temp.size();j++){
					if(temp.get(j).getAttributeValue(i).toString().compareTo(temp.get(minTuple).getAttributeValue(i).toString())<0)
						minTuple = j;
				}
				tuplesResult.add(temp.get(minTuple));
				temp.remove(minTuple);
			}
		}
		if (tuplesResult.size() > 0){
			return tuplesResult.remove(0);
		}
		else{
			return null;
		}
	}
	/**
     * The function is used to get the attribute list of the tuple
     * @return attribute list
     */
	public ArrayList<Attribute> getAttributeList(){
		return child.getAttributeList();
	}

}