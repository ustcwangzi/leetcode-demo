package com.wz.design;

import javafx.util.Pair;

import java.util.*;
import java.util.stream.Collectors;

/**
 * You have a movie renting company consisting of n shops. You want to implement a renting system that supports searching for, booking, and returning movies.
 * The system should also support generating a report of the currently rented movies.
 * Each movie is given as a 2D integer array entries where entries[i] = [shopi, moviei, pricei] indicates that there is
 * a copy of movie moviei at shop shopi with a rental price of pricei. Each shop carries at most one copy of a movie moviei.
 * The system should support the following functions:
 * - Search: Finds the cheapest 5 shops that have an unrented copy of a given movie. The shops should be sorted by price in ascending order,
 *           and in case of a tie, the one with the smaller shopi should appear first. If there are less than 5 matching shops,
 *           then all of them should be returned. If no shop has an unrented copy, then an empty list should be returned.
 * - Rent: Rents an unrented copy of a given movie from a given shop.
 * - Drop: Drops off a previously rented copy of a given movie at a given shop.
 * - Report: Returns the cheapest 5 rented movies (possibly of the same movie ID) as a 2D list res where res[j] = [shopj, moviej]
 *           describes that the jth cheapest rented movie moviej was rented from the shop shopj.
 *           The movies in res should be sorted by price in ascending order, and in case of a tie, the one with the smaller shopj should appear first,
 *           and if there is still tie, the one with the smaller moviej should appear first. If there are fewer than 5 rented movies,
 *           then all of them should be returned. If no movies are currently being rented, then an empty list should be returned.
 * Implement the MovieRentingSystem class:
 * MovieRentingSystem(int n, int[][] entries) Initializes the MovieRentingSystem object with n shops and the movies in entries.
 * List<Integer> search(int movie) Returns a list of shops that have an unrented copy of the given movie as described above.
 * void rent(int shop, int movie) Rents the given movie from the given shop.
 * void drop(int shop, int movie) Drops off a previously rented movie at the given shop.
 * List<List<Integer>> report() Returns a list of cheapest rented movies as described above.
 * Note: The test cases will be generated such that rent will only be called if the shop has an unrented copy of the movie,
 * and drop will only be called if the shop had previously rented out the movie.
 *
 * Example 1:
 * Input
 * ["MovieRentingSystem", "search", "rent", "rent", "report", "drop", "search"]
 * [[3, [[0, 1, 5], [0, 2, 6], [0, 3, 7], [1, 1, 4], [1, 2, 7], [2, 1, 5]]], [1], [0, 1], [1, 2], [], [1, 2], [2]]
 * Output
 * [null, [1, 0, 2], null, null, [[0, 1], [1, 2]], null, [0, 1]]
 * Explanation
 * MovieRentingSystem movieRentingSystem = new MovieRentingSystem(3, [[0, 1, 5], [0, 2, 6], [0, 3, 7], [1, 1, 4], [1, 2, 7], [2, 1, 5]]);
 * movieRentingSystem.search(1);  // return [1, 0, 2], Movies of ID 1 are unrented at shops 1, 0, and 2. Shop 1 is cheapest; shop 0 and 2 are the same price, so order by shop number.
 * movieRentingSystem.rent(0, 1); // Rent movie 1 from shop 0. Unrented movies at shop 0 are now [2,3].
 * movieRentingSystem.rent(1, 2); // Rent movie 2 from shop 1. Unrented movies at shop 1 are now [1].
 * movieRentingSystem.report();   // return [[0, 1], [1, 2]]. Movie 1 from shop 0 is cheapest, followed by movie 2 from shop 1.
 * movieRentingSystem.drop(1, 2); // Drop off movie 2 at shop 1. Unrented movies at shop 1 are now [1,2].
 * movieRentingSystem.search(2);  // return [0, 1]. Movies of ID 2 are unrented at shops 0 and 1. Shop 0 is cheapest, followed by shop 1.
 *
 * Constraints:
 * 1. 1 <= n <= 3 * 10^5
 * 2. 1 <= entries.length <= 10^5
 * 3. 0 <= shopi < n
 * 4. 1 <= moviei, pricei <= 10^4
 * 5. Each shop carries at most one copy of a movie moviei.
 * 6. At most 105 calls in total will be made to search, rent, drop and report.
 */
public class MovieRentingSystem {
    public static void main(String[] args) {
        MovieRentingSystem movieRentingSystem = new MovieRentingSystem(3, new int[][]{{0, 1, 5}, {0, 2, 6}, {0, 3, 7}, {1, 1, 4}, {1, 2, 7}, {2, 1, 5}});
        System.out.println(movieRentingSystem.search(1));
        movieRentingSystem.rent(0, 1);
        movieRentingSystem.rent(1, 2);
        System.out.println(movieRentingSystem.report());
        movieRentingSystem.drop(1, 2);
        System.out.println(movieRentingSystem.search(2));
    }

    /**
     * moveId -> Nodes
     */
    Map<Integer, Set<Node>> unrentedMap = new HashMap<>();
    /**
     * (shop, movie) -> price
     */
    Map<Pair<Integer, Integer>, Integer> priceMap = new HashMap<>();
    TreeSet<Node> rentedSet = new TreeSet<>();

    public MovieRentingSystem(int n, int[][] entries) {
        for (int[] entry : entries) {
            int shop = entry[0], movie = entry[1], price = entry[2];
            unrentedMap.computeIfAbsent(movie, x -> new TreeSet<>()).add(new Node(price, shop, movie));
            priceMap.put(new Pair<>(shop, movie), price);
        }
    }

    /**
     * 找到拥有指定电影且 未借出 的商店中 最便宜的 5 个
     * 按照 价格 升序排序，如果价格相同，则 shop 较小 的商店排在前面
     */
    public List<Integer> search(int movie) {
        return unrentedMap.getOrDefault(movie, Collections.emptySet()).stream().limit(5).map(e -> e.shop).collect(Collectors.toList());
    }

    /**
     * 从指定商店借出指定电影
     */
    public void rent(int shop, int movie) {
        int price = priceMap.get(new Pair<>(shop, movie));
        unrentedMap.get(movie).remove(new Node(price, shop, movie));
        rentedSet.add(new Node(price, shop, movie));
    }

    /**
     * 在指定商店返还 之前已借出 的指定电影
     */
    public void drop(int shop, int movie) {
        int price = priceMap.get(new Pair<>(shop, movie));
        unrentedMap.get(movie).add(new Node(price, shop, movie));
        rentedSet.remove(new Node(price, shop, movie));
    }

    /**
     * 返回 最便宜的 5 部已借出电影（可能有重复的电影 ID）
     * res[j] = [shopj, moviej] 表示第 j 便宜的已借出电影是从商店 shopj 借出的电影 moviej
     * res 中的电影需要按 价格 升序排序；如果价格相同，则 shop 较小 的排在前面；如果仍然相同，则 movie 较小 的排在前面
     */
    public List<List<Integer>> report() {
        return rentedSet.stream().limit(5).map(e -> Arrays.asList(e.shop, e.movie)).collect(Collectors.toList());
    }

    private static class Node implements Comparable<Node> {
        int price, shop, movie;

        public Node(int price, int shop, int movie) {
            this.price = price;
            this.shop = shop;
            this.movie = movie;
        }

        @Override
        public int compareTo(Node o) {
            if (this.price != o.price) {
                return Integer.compare(this.price, o.price);
            }
            if (this.shop != o.shop) {
                return Integer.compare(this.shop, o.shop);
            }
            return Integer.compare(this.movie, o.movie);
        }
    }
}
