
<div class="container-rate">
    <form id="formRate">
        <div id="rating">
            <input type="radio" id="star5" ${rateNo==5?"checked":""} disabled name="rating" value="5" />
            <label for="star5"  title="Awesome - 5 stars"></label>

            <input type="radio" id="star4" ${rateNo==4?"checked":""} disabled name="rating" value="4" />
            <label for="star4" title="Pretty good - 4 stars"></label>

            <input type="radio" id="star3" ${rateNo==3?"checked":""} disabled name="rating" value="3" />
            <label for="star3" title="Meh - 3 stars"></label>

            <input type="radio" id="star2" ${rateNo==2?"checked":""} disabled name="rating" value="2" />
            <label for="star2" title="Kinda bad - 2 stars"></label>

            <input type="radio" id="star1" ${rateNo==1?"checked":""} disabled name="rating" value="1" />
            <label for="star1" title="Sucks big time - 1 star"></label>
            <input type='hidden' value="${status=='Complete'?1:0}" id='status_complete' />
        </div>
    </form>
</div>
