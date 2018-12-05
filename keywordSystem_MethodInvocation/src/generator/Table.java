package generator;

import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

import basic.Expression;
import basic.Type;

public class Table {
	// table1 and table2 in all generators
	public static Map<Class<?>, Table[]> allTables = new HashMap<Class<?>, Table[]>();
	Vector<Map<Type, Vector<? extends Expression>>> table;

	public Table(Vector<Map<Type, Vector<? extends Expression>>> table) {
		this.table = table;
	}

	public void initializeAllTables() {

		for (Generator g : Generator.getAllGenetators()) {
			 if arity > 1 then it need to be add to depth 2
			// max beam width-th somethings in less and equals depth according to keywords
			Table table1 = new Table(new Vector<Map<Type,Vector<? extends Expression>>>());
			// max beam width-th somethings in exact depth according to keywords
			Table table2 = new Table(new Vector<Map<Type,Vector<? extends Expression>>>());
			initializeTable(table1,g);
			initializeTable(table2,g);
			allTables.put(g.getClass(), new Table[] {
					table1,table2
			});
		}
	}
	
	private void initializeTable(Table table_i,Generator g) {
		Map<Type, Vector<? extends Expression>> initialElement = new HashMap<Type, Vector<? extends Expression>>();
		for (Type t : g.getAllReceiverTypeName()) {
			initialElement.put(t, new Vector<Expression>());
		}
		table_i.table.add(initialElement);

	}
	
}
