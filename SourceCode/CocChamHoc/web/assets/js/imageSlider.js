const slider = document.querySelector(".slider");
const nextBtn = document.querySelector(".next-btn");
const prevBtn = document.querySelector(".prev-btn");
const slides = document.querySelectorAll(".slide");
const slideIcons = document.querySelectorAll(".slide-icon");
const numberOfSlides = slides.length;
var slideNumber = 0;

var manualNav = function (manual) {
  slides.forEach((slide) => {
    slide.classList.remove('active');

    slideIcons.forEach((slide-icon) =>{
      slide-icon.classList.remove('active');
    });
  });

  slides[manual].classList.add("active");
  slideIcons[manual].classList.add("active");
}

 slideIcons.forEach((slide-icon, i) => {
    slide-icon.addEventListener("click", () => {
    manualNav(i);
    currentSlide = i;
    });
 });







// image slider next button
nextBtn.addEventListener("click", () => {
  slides.forEach((slide) => {
    slide.classList.remove("active");
  });

  slideIcons.forEach((slideIcons) => {
    slideIcons.classList.remove("active");
  });

  slideNumber++;

  if (slideNumber > numberOfSlides - 1) {
    slideNumber = 0;
  }

  slides[slideNumber].classList.add("active");
  slideIcons[slideNumber].classList.add("active");
});

// image slider previous button
prevBtn.addEventListener("click", () => {
  slides.forEach((slide) => {
    slide.classList.remove("active");
  });

  slideIcons.forEach((slideIcons) => {
    slideIcons.classList.remove("active");
  });

  slideNumber--;

  if (slideNumber < 0) {
    slideNumber = numberOfSlides - 1;
  }

  slides[slideNumber].classList.add("active");
  slideIcons[slideNumber].classList.add("active");
});

// image slider autoplay
var playSlider;

var reapeater = (e) => {
  playSlider = setInterval(function () {
    slides.forEach((slide) => {
      slide.classList.remove("active");
    });

    slideIcons.forEach((slideIcons) => {
      slideIcons.classList.remove("active");
    });

    slideNumber++;

    if (slideNumber > numberOfSlides - 1) {
      slideNumber = 0;
    }

    slides[slideNumber].classList.add("active");
    slideIcons[slideNumber].classList.add("active");
  }, 2000);
};
reapeater();

// stop the image slider autoplay
slider.addEventListener("mouseover", () => {
  clearInterval(playSlider);
});

// start the image slider autoplay
slider.addEventListener("mouseout", () => {
  reapeater();
});
