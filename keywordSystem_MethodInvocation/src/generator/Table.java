package generator;

import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

import basic.Expression;
import basic.Type;

public class Table {
	Vector<Map<Type, Vector<Expression>>> mappingFromTypeToExps;

	public Table(Vector<Map<Type, Vector<Expression>>> mappingFromTypeToExps) {
		this.mappingFromTypeToExps = mappingFromTypeToExps;
	}

	// table1 and table2 in all generators
	public static Map<Class<?>, PairOfTable> allTables = new HashMap<Class<?>, PairOfTable>();

	public static void initializeAllTables(int depth) {
		for (Generator g : Generator.getAllGenetators()) {
			// System.out.println("name 1 : " + g.getClass().getName());
			Table table1 = new Table(new Vector<Map<Type, Vector<Expression>>>());
			Table table2 = new Table(new Vector<Map<Type, Vector<Expression>>>());
			initializeTable(table1, g, depth);
			initializeTable(table2, g, depth);
			PairOfTable tables = new PairOfTable(table1, table2);
			allTables.put(g.getClass(), tables);
		}
	}

	// if arity > 1 then it need to be add to depth 2
	private static void initializeTable(Table table, Generator g, int depth) {
		Map<Type, Vector<Expression>> initialElement = new HashMap<Type, Vector<Expression>>();
		for (Type t : g.getAllReceiveTypeName()) {
			initialElement.put(t, new Vector<Expression>());
		}
		for (int i = 0; i < depth; i++) {
			table.mappingFromTypeToExps.add(initialElement);
		}

	}

}

class PairOfTable {
	// max beam width-th somethings in less and equals depth according to keywords
	Table table1;
	// max beam width-th somethings in exact depth according to keywords
	Table table2;

	public PairOfTable(Table table1, Table table2) {
		this.table1 = table1;
		this.table2 = table2;
	}
}
