/*
 * The MIT License (MIT)
 *
 * Copyright (c) 2014 Sada Kurapati <sadakurapati@gmail.com>
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */

package com.sada.quora;

import java.util.*;

//TODO - in progress. complete it when you get some time.
public class Nearby {
    private Topic[] topics;
    private HashMap<Integer, List<Integer>> mapQuestions;

    public Nearby(Topic[] t) {
        topics = t;
        Arrays.sort(topics);
        mapQuestions = new HashMap<Integer, List<Integer>>(t.length);
    }

    public static void main(String[] args) {
        //reading input
        Scanner sc = new Scanner(System.in);
        int nTopics = sc.nextInt();
        int nQuestions = sc.nextInt();
        int nQueries = sc.nextInt();
        List<Topic> listTopics = new ArrayList<Topic>();
        //read Topics
        for (int i = 0; i < nTopics; i++) {
            //topic id
            Topic t = new Topic(sc.nextInt(), getDistance(sc.nextDouble(), sc.nextDouble()));
            listTopics.add(t);
        }
        Nearby nearby = new Nearby(listTopics.toArray(new Topic[listTopics.size()]));
        //read Questions
        for (int i = 0; i < nQuestions; i++) {
            //question id
            Integer qId = sc.nextInt();
            int qTopics = sc.nextInt();
            for (int j = 0; j < qTopics; j++) {
                Integer tId = sc.nextInt();
                nearby.addQuestion(qId, tId);
            }
        }

        //reading Queries
        for (int i = 0; i < nQueries; i++) {
            //is it topic or question
            String type = sc.next();
            char c = type.charAt(0);
            int count = sc.nextInt();
            Double start = getDistance(sc.nextDouble(), sc.nextDouble());
            if (c == 't') {
                nearby.printTopics(start, count);
            } else {
                nearby.printQuestions(start, count);
            }
            //System.out.println();
        }
        //System.out.println("End");
    }

    public void printTopics(Double start, int count) {
        if (count > 0) {
            int at = nearestTopicAt(start);
            if (at >= 0) {
                StringBuilder output = new StringBuilder();
                TopicCursor cursor = new TopicCursor(at, at - 1, at + 1);
                count--;
                Topic currTopic = topics[at];
                output.append(currTopic.id);
                output.append(" ");
                //System.out.printf("%d ", currTopic.id);
                while (count > 0 && null != currTopic) {
                    currTopic = getNextTopic(cursor, start);
                    count--;
                    //System.out.printf("%d ", currTopic.id);
                    output.append(currTopic.id);
                    output.append(" ");
                }
                output.setLength(output.length() - 1);
                System.out.println(output.toString());
            }
        }
    }

    public void printQuestions(Double start, int count) {
        if (count > 0) {
            int at = nearestTopicAt(start);
            if (at >= 0) {
                StringBuilder output = new StringBuilder();
                TopicCursor cursor = new TopicCursor(at, at - 1, at + 1);
                Topic currTopic = topics[at];
                Set<Integer> visited = new HashSet<Integer>();
                while (count > 0 && null != currTopic) {
                    //get all questions
                    List<Integer> list = mapQuestions.get(currTopic.id);
                    if (null != list && list.size() > 0) {
                        //TODO - avoid sorting every time.
                        Collections.sort(list, Collections.reverseOrder());
                        for (int i = 0; i < list.size() && count > 0; i++) {
                            if (!visited.contains(list.get(i))) {
                                //System.out.printf("%d ", list.get(i));
                                output.append(list.get(i));
                                output.append(" ");
                                count--;
                                visited.add(list.get(i));
                            }
                        }
                    }
                    currTopic = getNextTopic(cursor, start);
                }
                //just to avoid last extra space for printing.
                if (output.length() > 0) {
                    output.setLength(output.length() - 1);
                    System.out.println(output.toString());
                }
            }
        }
    }

    public int nearestTopicAt(Double start) {
        //TODO - do binary search
        int at = -1;
        if (topics.length > 0) {
            at = 0;
            Double preDiff = Math.abs(start - topics[0].distance);
            for (int i = 1; i < topics.length; i++) {
                Double currDiff = Math.abs(start - topics[i].distance);
                if (currDiff < preDiff) {
                    preDiff = currDiff;
                    at = i;
                } else {
                    //going far, so break the loop
                    break;
                }
            }
        }
        return at;
    }

    public Topic getNextTopic(TopicCursor cursor, Double origin) {
        Topic answer = null;
        if (cursor.left >= 0 && cursor.right < topics.length) {
            //both side element exist so compare the distance
            Double leftDiff = Math.abs(origin - topics[cursor.left].distance);
            Double rightDiff = Math.abs(origin - topics[cursor.right].distance);
            if (leftDiff > rightDiff) {
                answer = topics[cursor.left];
                cursor.left--;
            } else {
                answer = topics[cursor.right];
                cursor.right++;
            }
        } else if (cursor.left >= 0) {
            answer = topics[cursor.left];
            cursor.left--;
        } else if (cursor.right < topics.length) {
            answer = topics[cursor.right];
            cursor.right++;
        }
        return answer;
    }

    public static Double getDistance(Double x, Double y) {
        return Math.sqrt((x * x) + (y * y));
    }

    public void addQuestion(Integer q, Integer t) {
        //list of questions for this topic
        List<Integer> qSet = mapQuestions.get(t);
        //if null, create a new list
        if (null == qSet) {
            qSet = new ArrayList<Integer>();
            mapQuestions.put(t, qSet);
        }
        //add the topic
        qSet.add(q);
    }
}

class TopicCursor {
    int origin;
    int left;
    int right;

    TopicCursor(int origin, int left, int right) {
        this.origin = origin;
        this.left = left;
        this.right = right;
    }
}

class Topic implements Comparable<Topic> {
    Integer id;
    Double distance;

    Topic(Integer id, Double distance) {
        this.id = id;
        this.distance = distance;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Topic topic = (Topic) o;

        if (id != null ? !id.equals(topic.id) : topic.id != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }

    @Override
    public int compareTo(Topic o) {
        if (Math.abs(distance - o.distance) < 0.001) {
            return id.compareTo(o.id);
        } else {
            return distance.compareTo(o.distance);
        }
    }
}

