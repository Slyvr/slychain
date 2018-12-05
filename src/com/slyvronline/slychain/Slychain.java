package com.slyvronline.slychain;

import java.util.ArrayList;

import com.google.gson.GsonBuilder;

public class Slychain {

	public static ArrayList<Block> blockchain = new ArrayList<Block>();
	
	public static void main(String[] args){
		blockchain.add(new Block("First block!", "0"));
		blockchain.add(new Block("Second block!", blockchain.get(blockchain.size() - 1).getHash()));
		blockchain.add(new Block("Third block!", blockchain.get(blockchain.size() - 1).getHash()));
		
		String blockchainJson = new GsonBuilder().setPrettyPrinting().create().toJson(blockchain);		
		System.out.println(blockchainJson);
	}
	
	public static Boolean isChainValid(){
		Block currentBlock;
		Block previousBlock;
		
		for(int i=1; i<blockchain.size(); i++){
			currentBlock = blockchain.get(i);
			previousBlock = blockchain.get(i-1);
			if (!currentBlock.getHash().equals(currentBlock.calculateHash())){
				System.out.println("Current Hashes not equal");
				return false;
			}
			if (!previousBlock.getHash().equals(currentBlock.getPreviousHash())){
				System.out.println("Previous Hashes not equal");
				return false;
			}
		}
		return true;
	}
}
