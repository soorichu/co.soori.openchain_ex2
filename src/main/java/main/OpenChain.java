package main;
import com.google.gson.GsonBuilder;
import core.Block;
import core.Transaction;
import core.TransactionOutput;
import core.Wallet;
import util.StringUtil;

import java.security.Security;
import java.util.ArrayList;
import java.util.HashMap;

public class OpenChain{
    public static ArrayList<Block> blockchain = new ArrayList<Block>();
    public static HashMap<String, TransactionOutput> UTXOs = new HashMap<String, TransactionOutput>();
    public static int difficulty = 2;
    public static float minimumTransaction = 0.1f;
    public static Wallet walletA;
    public static Wallet walletB;

    public static void main(String[] arg){

        Security.addProvider(new org.bouncycastle.jce.provider.BouncyCastleProvider());

        walletA = new Wallet();
        walletB = new Wallet();

        System.out.println("Private and public keys:");
        System.out.println(StringUtil.getStringFromKey(walletA.privateKey));
        System.out.println(StringUtil.getStringFromKey(walletA.publicKey));

        Transaction transaction = new Transaction(walletA.publicKey, walletB.publicKey, 5, null);
        transaction.generateSignature(walletA.privateKey);

        System.out.println("Is signature verified");
        System.out.println(transaction.verifySignature());

        /*
        blockchain.add(new Block("Genesis block", "0"));
        System.out.println("\n Trying to Mine Genesis block");
        blockchain.get(0).mineBlock(difficulty);

        for(int i = 1; i<=1; i++){
            blockchain.add(new Block("block" + i, blockchain.get(blockchain.size()-1).hash));
            System.out.printf("\nTryting to Mine block #%d", i+1);
            blockchain.get(i).mineBlock(difficulty);
        }

        String blockchainJson = new GsonBuilder().setPrettyPrinting().create().toJson(blockchain);
        System.out.println("\nOpenchain Block list : ");
        System.out.println(blockchainJson);
        */
    }

    public static Boolean isChainValid(){
        Block currentBlock;
        Block previousBlock;

        for(int i =1; i < blockchain.size(); i++){
            currentBlock = blockchain.get(i);
            previousBlock = blockchain.get(i-1);

            if(!currentBlock.hash.equals(currentBlock.previousHash)){
                System.out.println("Previous Hashes not equal");
                return false;
            }

        }
        return true;
    }
}