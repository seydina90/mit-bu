/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.views.admin;

/**
 *
 * @author Idriss
 */
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.SortedSet;
import java.util.TreeSet;

import javax.swing.AbstractListModel;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class auteurListBox extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@SuppressWarnings("rawtypes")
	private JList sourceList;

	private auteurListModel sourceListModel;

	@SuppressWarnings("rawtypes")
	private JList destList;

	private auteurListModel destListModel;

	private JButton addButton;

	private JButton removeButton;

	public auteurListBox() {
		setOpaque(false);
		initScreen();
	}

	// getDestinationElements
	public String[] getDestinationElements() {
		return destListModel.getAll();
	}

	// addDestinationElements
	public void addDestinationElements(Object newValue[]) {
		destListModel.addAll(newValue);
	}

	// addDestinationElements
	public void addDestinationElements(ArrayList<String> list) {
		destListModel.addAll(list);
	}

	// addSourceElements
	public void addSourceElements(Object newValue[]) {
		sourceListModel.addAll(newValue);
	}

	// addSourceElements
	public void addSourceElements(ArrayList<String> list) {
		sourceListModel.addAll(list);
	}

	public void clearDestinationListModel() {
		destListModel.clear();
	}

	public void clearSourceListModel() {
		sourceListModel.clear();
	}

	// clearSourceSelected
	private void clearSourceSelected() {
		@SuppressWarnings("deprecation")
		Object selected[] = sourceList.getSelectedValues();
		for (int i = selected.length - 1; i >= 0; --i) {
			sourceListModel.removeElement(selected[i]);
		}
		sourceList.getSelectionModel().clearSelection();
	}

	// clearDestinationSelected
	private void clearDestinationSelected() {
		@SuppressWarnings("deprecation")
		Object selected[] = destList.getSelectedValues();
		for (int i = selected.length - 1; i >= 0; --i) {
			destListModel.removeElement(selected[i]);
		}
		destList.getSelectionModel().clearSelection();
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	private void initScreen() {
		setBorder(BorderFactory.createEtchedBorder());
		sourceListModel = new auteurListModel();
		setLayout(null);
		sourceList = new JList(sourceListModel);
		JScrollPane scrollPane = new JScrollPane(sourceList);
		scrollPane.setBounds(0, 2, 118, 118);
		add(scrollPane);

		addButton = new JButton(">>");
		addButton.setBounds(69, 126, 49, 23);
		addButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				@SuppressWarnings("deprecation")
				Object selected[] = sourceList.getSelectedValues();
				addDestinationElements(selected);
				clearSourceSelected();
			}
		});
		add(addButton);
		removeButton = new JButton("<<");
		removeButton.setBounds(128, 126, 49, 23);
		removeButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				@SuppressWarnings("deprecation")
				Object selected[] = destList.getSelectedValues();
				addSourceElements(selected);
				clearDestinationSelected();
			}
		});
		add(removeButton);

		destListModel = new auteurListModel();
		destList = new JList(destListModel);
		JScrollPane scrollPane_1 = new JScrollPane(destList);
		scrollPane_1.setBounds(128, 2, 113, 118);
		add(scrollPane_1);
	}

	class auteurListModel extends AbstractListModel<Object> {

		/**
	 * 
	 */
		private static final long serialVersionUID = 1L;
		SortedSet<Object> model;

		public auteurListModel() {
			model = new TreeSet<Object>();
		}

		public int getSize() {
			return model.size();
		}

		public Object getElementAt(int index) {
			return model.toArray()[index];
		}

		public void add(Object element) {
			if (model.add(element)) {
				fireContentsChanged(this, 0, getSize());
			}
		}

		public void addAll(Object elements[]) {
			Collection<Object> c = Arrays.asList(elements);
			model.addAll(c);
			fireContentsChanged(this, 0, getSize());
		}

		public void addAll(ArrayList<String> list) {
			for (int i = 0; i < list.size(); i++) {
				add((Object) list.get(i));
			}
		}

		public void clear() {
			model.clear();
			fireContentsChanged(this, 0, getSize());
		}

		public String[] getAll() {
			int size = getSize();
			String[] tab = new String[size];
			for (int i = 0; i < size; i++)
				tab[i] = model.toArray()[i].toString();
			return tab;
		}

		public boolean removeElement(Object element) {
			boolean removed = model.remove(element);
			if (removed) {
				fireContentsChanged(this, 0, getSize());
			}
			return removed;
		}
	}
}
