import {useMemo} from "react";

export const useSortedPosts = (posts, sort) => {
    return useMemo(() => {
        console.log(sort)
        switch (sort) {
            case "username":
                return [...posts].sort((p1, p2) => p1.user.username.localeCompare(p2.user.username))
            case "text":
                return [...posts].sort((p1, p2) => p1.text.localeCompare(p2.text))
            default:
                return posts
        }
    }, [sort, posts])
}

export const usePosts = (posts, sort, search) => {
    const sortedPosts = useSortedPosts(posts, sort)
    return useMemo(() => {
        return [...sortedPosts].filter(p => p.user.username.toLowerCase().includes(search.toLowerCase()));
    }, [search, sortedPosts])
}