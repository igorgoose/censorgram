import {useMemo} from "react";

export const useSortedPosts = (posts, sort) => {
    return useMemo(() => {
        console.log(sort)
        switch (sort) {
            case "title":
                return [...posts].sort((p1, p2) => p1.title.localeCompare(p2.title))
            case "body":
                return [...posts].sort((p1, p2) => p1.body.localeCompare(p2.body))
            default:
                return posts
        }
    }, [sort, posts])
}

export const usePosts = (posts, sort, search) => {
    const sortedPosts = useSortedPosts(posts, sort)
    return useMemo(() => {
        return [...sortedPosts].filter(p => p.title.toLowerCase().includes(search.toLowerCase()))
    }, [search, sortedPosts])
}