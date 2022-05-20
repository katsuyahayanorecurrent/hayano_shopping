package com.example.demo.Entity;

import java.util.HashMap;
import java.util.Map;


public class Cart {
	private Map<Integer, Items> items = new HashMap<>();
	private int total;
	
	public Map<Integer, Items> getItems() {
		return items;
	}

	public int getTotal() {
		return total;
	}

	// コンストラクタ
	public Cart() {
	}

	/**
	 * アイテム情報をカートに追加
	 * 
	 * @param items
	 * @param quantity
	 */
	public void addCart(Items item, int quantity) {
		Items existedItems = items.get(item.getId());

		// アイテムが存在しない場合は追加
		if (existedItems == null) {
			// 数量を設定
			item.setQuantity(quantity);
			// リストに追加
			items.put(item.getId(), item);

			// アイテムが存在する場合は追加しない（重複するため）
		} else {
			existedItems.setQuantity(existedItems.getQuantity() + quantity);
		}
		recalcTotal();
	}

	/**
	 * カートの削除処理
	 * 
	 * @param itemCode
	 */
	public void deleteCart(int itemCode) {
		// itemCodeを利用してカート配列から削除
		items.remove(itemCode);
		recalcTotal();
	}

	/**
	 * カートの中身の総金額を算出する処理 th:each = "変数 : 配列"
	 */
	public void recalcTotal() {
		total = 0;
		for (Items items : items.values()) {
			total += items.getPrice() * items.getQuantity();
		}
	}
}

