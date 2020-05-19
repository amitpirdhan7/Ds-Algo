package practise;

import java.util.Stack;

/** Problem statement -
 *
 * Write a class StockSpanner which collects daily price quotes for some stock, and returns the span of that stock's price for the current day.

 The span of the stock's price today is defined as the maximum number of consecutive days (starting from today and going backwards) for which the price of the stock was less than or equal to today's price.

 For example, if the price of a stock over the next 7 days were [100, 80, 60, 70, 60, 75, 85], then the stock spans would be [1, 1, 1, 2, 1, 4, 6].
 *
 *
 *
 * Input: ["StockSpanner","next","next","next","next","next","next","next"], [[],[100],[80],[60],[70],[60],[75],[85]]
 Output: [null,1,1,1,2,1,4,6]
 Explanation:
 First, S = StockSpanner() is initialized.  Then:
 S.next(100) is called and returns 1,
 S.next(80) is called and returns 1,
 S.next(60) is called and returns 1,
 S.next(70) is called and returns 2,
 S.next(60) is called and returns 1,
 S.next(75) is called and returns 4,
 S.next(85) is called and returns 6.

 Note that (for example) S.next(75) returned 4, because the last 4 prices
 (including today's price of 75) were less than or equal to today's price.
 * Created by amitsharma on 19/5/20.
 */
public class StockSpanner {

    class StockNode {

     public int price;

     public int index;

     public StockNode(int price, int index) {

       this.price = price;
       this.index = index;
     }
   }

  // For approach1
  //private List<Integer> stockPriceList;

  private Stack<StockNode> stack;

  private int currentIndex = 0;

  public StockSpanner() {
    stack = new Stack<>();
  }

  //Final
  public int next(int price) {
    currentIndex = currentIndex + 1;
    if(stack.isEmpty()) {
      stack.push(new StockNode(price, currentIndex));
      return currentIndex;
    }

    StockNode element = stack.peek();
    int span = 0;
    if(price < element.price) {
       span = currentIndex - element.index;
    } else {
      while(price >= element.price) {
        stack.pop();
        if(stack.isEmpty()) {
          break;
        }
        element = stack.peek();
      }
      if(stack.isEmpty()) {
        span = currentIndex;
      } else {
        span = currentIndex - element.index;
      }
    }
    stack.push(new StockNode(price, currentIndex));
    return span;
  }

//  public int nextAppraoch1(int price) {
//    int span = 1;
//    if(stockPriceList == null || stockPriceList.isEmpty()
//        || price < stockPriceList.get(stockPriceList.size()-1)) {
//      stockPriceList.add(price);
//      return span;
//    }
//
//    int j = stockPriceList.size() - 1;
//    while(j >= 0 && price >= stockPriceList.get(j)) {
//      span++;
//      j--;
//    }
//
//    stockPriceList.add(price);
//    return span;
//  }

}
